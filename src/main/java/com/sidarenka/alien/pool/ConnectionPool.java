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

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The free connections. */
    private BlockingQueue<ProxyConnection> freeConnections;
    
    /** The working connections. */
    private BlockingQueue<ProxyConnection> workingConnections;
    
    /** The Constant DEFAULT_POOL_SIZE. */
    private static final int DEFAULT_POOL_SIZE = 5;
    
    /** The instance. */
    private static ConnectionPool instance;
    
    /** The create lock. */
    private static ReentrantLock createLock = new ReentrantLock();
    
    /** The is create. */
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    
    /** The driver name. */
    private String driverName;
    
    /** The url. */
    private String url;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The pool size. */
    private int poolSize;



    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the pool size.
     *
     * @return the pool size
     */
    public int getPoolSize() {
        return poolSize;
    }

    /**
     * Sets the pool size.
     *
     * @param poolSize the new pool size
     */
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    /**
     * Instantiates a new connection pool.
     */
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

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
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


    /**
     * Inits the.
     *
     * @throws ConnectionPoolException the connection pool exception
     */
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

    /**
     * Take connection.
     *
     * @return the proxy connection
     * @throws ConnectionPoolException the connection pool exception
     */
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

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(ProxyConnection connection) {
        try {
            workingConnections.remove(connection);
            freeConnections.put(connection);
            logger.log(Level.INFO, "Success pool release connection");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Clear connection queue.
     *
     * @throws SQLException the SQL exception
     */
    private void clearConnectionQueue() throws SQLException {
        ProxyConnection connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.realClose();
        }
    }

    /**
     * Close pool.
     */
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
                logger.error(e);
            }
        }

    }
}

