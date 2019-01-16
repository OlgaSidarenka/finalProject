package com.sidarenka.alien.service.impl;

import com.sidarenka.alien.dao.DaoFactory;
import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.impl.AlienDaoImpl;
import com.sidarenka.alien.dao.impl.UserDaoImpl;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.pool.ConnectionPoolException;
import com.sidarenka.alien.pool.ProxyConnection;
import com.sidarenka.alien.resource.ConfigurationManager;

import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import com.sidarenka.alien.util.PasswordEncoder;
import com.sidarenka.alien.validator.UserValidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.dao.ColumnName.*;
import static com.sidarenka.alien.dao.ColumnName.AVERAGE_MARK;
import static com.sidarenka.alien.dao.ColumnName.HOMELAND_NAME;
import static com.sidarenka.alien.dao.SqlQuery.SQL_SELECT_AII_ALIENS;

/**
 * The Class UserServiceImpl.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User login(String login, String password) throws ServiceException {
        String encodedPassword = new String(PasswordEncoder.encodePassword(password));
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        User currentUser;
        try {
            currentUser = userDaoImpl.findByLoginAndPassword(login, encodedPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return currentUser;
    }

    @Override
    public List<User> registration(String login, String password, String email) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<User> users = new ArrayList<>();
        try {
            if (!userDaoImpl.findByLogin(login)) {
                if (!UserValidator.validateUserData(login, password, email)) {
                    throw new ServiceException("Incorrect registration data");
                }
                User user = new User();
                user.setLogin(login);
                String encodedPassword = new String(PasswordEncoder.encodePassword(password));
                user.setPassword(encodedPassword);
                user.setEmail(email);
                userDaoImpl.create(user);
                users.add(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> selectAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<User> users;
        try {
            users = userDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public void changeStatus(String selectedStatus, String selectedUser) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        try {
            userDaoImpl.updateUserStatus(selectedStatus, selectedUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long takeUserId(String login) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        long userId;
        try {
            User user = userDaoImpl.findByLoginForReview(login);
            userId = user.getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userId;
    }

    @Override
    public List<Review> findUserReviews(long userId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<Review> reviews;
        try {
            reviews = userDaoImpl.findUserReviews(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public void blockUserReview(long reviewId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        try {
            userDaoImpl.blockUserReview(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Alien> findRatedAliensForUser(long userId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens;
        try {
            aliens = alienDaoImpl.findRatedAliensForUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }
}

