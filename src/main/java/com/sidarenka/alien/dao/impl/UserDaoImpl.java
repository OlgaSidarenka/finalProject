package com.sidarenka.alien.dao.impl;

import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.UserDao;
import com.sidarenka.alien.entity.*;
import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.pool.ConnectionPoolException;
import com.sidarenka.alien.pool.ProxyConnection;
import com.sidarenka.alien.util.PasswordDecoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.dao.ColumnName.*;
import static com.sidarenka.alien.dao.SqlQuery.*;
import static com.sidarenka.alien.entity.StatusType.NEWCOMER;

public class UserDaoImpl implements UserDao {
    private static final int DEFAULT_USER_ROLE_ID = 2;
    private static final int DEFAULT_USER_STATUS_ID = 1;

    public User findByLoginAndPassword(String login, String password) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String currentLogin = resultSet.getString(USER_LOGIN);
                String currentPassword = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                int roleId = resultSet.getInt(ROLE_ID);
                int statusId = resultSet.getInt(STATUS_ID);
                user = new User(currentLogin, currentPassword, email, RoleType.takeRole(roleId), StatusType.takeStatus(statusId));
            }
            return user;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    public boolean findByLogin(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public User findByLoginForReview(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String currentLogin = resultSet.getString(USER_LOGIN);
                long currentUserId = resultSet.getLong(USER_ID);
                user = new User(currentUserId, currentLogin);
            }
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_AII_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong(USER_ID));
                user.setLogin(resultSet.getString(USER_LOGIN));
                String encodedPassword = resultSet.getString(PASSWORD);
                user.setPassword(PasswordDecoder.decodePassword(encodedPassword));
                user.setEmail(resultSet.getString(EMAIL));
                user.setUserRole(RoleType.takeRole(resultSet.getInt(ROLE_ID)));
                user.setUserStatus(StatusType.takeStatus(resultSet.getInt(STATUS_ID)));
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return users;
    }


    @Override
    public boolean findById(long id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public void create(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setLong(4, DEFAULT_USER_ROLE_ID);
            statement.setInt(5, DEFAULT_USER_STATUS_ID);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void updateUserStatus(String status, String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_STATUS);
            statement.setInt(1, Integer.parseInt(status));
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    @Override
    public User update(User entity) {
        return null;
    }

    public List<Review> findUserReviews(long userId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWS);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setAlienName(resultSet.getString(ALIEN_NAME));
                review.setLogin(resultSet.getString(USER_LOGIN));
                review.setTextReview(resultSet.getString(TEXT_REVIEW));
                review.setDateReview(resultSet.getDate(DATE_REVIEW));
                reviews.add(review);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return reviews;
    }

}

