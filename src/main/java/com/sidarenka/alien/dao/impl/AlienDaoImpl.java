package com.sidarenka.alien.dao.impl;

import com.sidarenka.alien.dao.AlienDao;
import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.entity.*;
import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.pool.ConnectionPoolException;
import com.sidarenka.alien.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.dao.ColumnName.*;
import static com.sidarenka.alien.dao.SqlQuery.*;

public class AlienDaoImpl implements AlienDao {
    @Override
    public List<Alien> findAll() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Alien> aliens = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_AII_ALIENS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Alien alien = new Alien();
                alien.setAlienId(resultSet.getLong(ALIEN_ID));
                alien.setAlienName(resultSet.getString(ALIEN_NAME));
                alien.setDescription(resultSet.getString(ALIEN_DESCRIPTION));
                alien.setHomeland(new Homeland(resultSet.getLong(HOMELAND_ID), resultSet.getString(HOMELAND_NAME)));
                alien.setAverageMark(resultSet.getDouble(AVERAGE_MARK));
                aliens.add(alien);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return aliens;
    }

    @Override//TODO Return List<Alien>
    public boolean findById(long id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ALIEN_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
    public boolean findMarkByUserIdAndAlienId(long userId,long alienId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ALIEN_MARK_FROM_USER);
            statement.setLong(1, userId);
            statement.setLong(2, alienId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean delete(long id) {
//        ProxyConnection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = ConnectionPool.getInstance().takeConnection();
//            statement = connection.prepareStatement(SQL_DELETE_ALIEN_BY_ID);
//            statement.setLong(1, id);
//            resultSet = statement.executeQuery();
//            if (!resultSet.next()) {
//                return null;
//            } else {
//                return new Alien();
//            }
//        } catch (ConnectionPoolException | SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            UserDaoImpl userDAO = new UserDaoImpl();
//            userDAO.close(statement);
//            userDAO.close(connection);
//        }

        return false;
    }

    @Override
    public boolean delete(Alien entity) {
        return false;
    }

    @Override
    public void create(Alien alien) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_ALIEN);
            statement.setString(1, alien.getAlienName());
            statement.setString(2, alien.getDescription());
            statement.setLong(3, alien.getHomeland().getHomelandId());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Alien update(Alien alien) {

        return null;
    }

    @Override
    public boolean findAlienByName(String alienName) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ALIEN_BY_NAME);
            statement.setString(1, alienName);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
    public Alien findAlienByName1(String alienName) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Alien alien=null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ALIEN_BY_NAME);
            statement.setString(1, alienName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                alien = new Alien();
                alien.setAlienId(resultSet.getLong(ALIEN_ID));
                alien.setAlienName(resultSet.getString(ALIEN_NAME));
                alien.setDescription(resultSet.getString(ALIEN_DESCRIPTION));
                alien.setHomeland(new Homeland(resultSet.getLong(HOMELAND_ID), resultSet.getString(HOMELAND_NAME)));
                alien.setAverageMark(resultSet.getDouble(AVERAGE_MARK));

            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return alien;
    }


    public List<Review> findAlienReviews(long alienId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALIEN_REVIEWS);
            statement.setLong(1, alienId);
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


    public void createReview(Review review) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_REVIEW);
            statement.setLong(1, review.getAlienId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getTextReview());
            statement.setDate(4, review.getDateReview());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }

    }

    @Override
    public Homeland createHomeland(Homeland homeland) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_HOMELAND);
            statement.setString(1, homeland.getHomelandName());
            statement.executeUpdate();
            return homeland;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Homeland> findHomelandByName(String homelandName) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Homeland> homelands = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_HOMELAND_ID_BY_HOMELAND_NAME);
            statement.setString(1, homelandName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Homeland homeland = new Homeland();
                homeland.setHomelandName(homelandName);
                homeland.setHomelandId(resultSet.getLong("homelandId"));
                homelands.add(homeland);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return homelands;
    }
    public void insertMark(Mark mark) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_ALIEN_MARK);
            statement.setLong(1, mark.getUserId());
            statement.setLong(2, mark.getAlienId());
            statement.setInt(3,mark.getMark());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}