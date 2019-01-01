package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.Entity;
import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AbstractDao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    boolean findById(long id) throws DaoException;

    boolean delete(long id);

    boolean delete(T entity);

    void create(T entity) throws DaoException;

    T update(T entity);

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Unable to close connection");
        }
    }

    default void close(ProxyConnection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
