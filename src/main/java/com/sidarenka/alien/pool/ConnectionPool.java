package com.sidarenka.alien.pool;

import com.sidarenka.alien.resource.DbResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> workingConnections;
    private static final int DEFAULT_POOL_SIZE = 5;
    private static ConnectionPool instance;
    private static ReentrantLock createLock = new ReentrantLock();
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    private String driverName;
    private String url;
    private String username;
    private String password;
    private int poolSize;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public ConnectionPool() {
        DbResourceManager dbResourceManager = DbResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DbParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DbParameter.DB_URL);
        this.username = dbResourceManager.getValue(DbParameter.DB_USER);
        this.password = dbResourceManager.getValue(DbParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DbParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        }
        try {
            init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreate.get()) {
            try {
                createLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreate.set(true);
                }
            } finally {
                createLock.unlock();
            }
        }
        return instance;
    }


    public void init() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            workingConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
            logger.log(Level.INFO, "Success pool init");
        } catch (SQLException e) {
            throw new ConnectionPoolException("Sql exception in connectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cant find database driver class", e);
        }
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            workingConnections.put(connection);
            logger.log(Level.INFO, "Success take pool connection");
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source", e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            workingConnections.remove(connection);
            freeConnections.put(connection);
            logger.log(Level.INFO, "Success pool release connection");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    private void clearConnectionQueue() throws SQLException {
        ProxyConnection connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.realClose();
        }
    }

    public void closePool() {
        try {
        java.sql.Driver sqlDriver = DriverManager.getDriver(DbParameter.DB_URL);
        DriverManager.deregisterDriver(sqlDriver);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        if (instance != null) {
            try {
                instance.clearConnectionQueue();
                instance = null;
                logger.log(Level.INFO, "Success pool close");

            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }

    }
}

