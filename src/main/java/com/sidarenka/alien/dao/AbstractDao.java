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
   String UNABLE_CLOSE_CONNECTION_MESSAGE="Unable to close connection";
  Logger logger = LogManager.getLogger();
    List<T> findAll() throws DaoException;
    boolean findById(long id) throws DaoException;
    void create(T entity) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, UNABLE_CLOSE_CONNECTION_MESSAGE);
        }
    }

    default void close(ProxyConnection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
