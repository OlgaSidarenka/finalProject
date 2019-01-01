package com.sidarenka.alien.service;

import com.sidarenka.alien.dao.DaoFactory;
import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.impl.UserDaoImpl;
import com.sidarenka.alien.entity.StatusType;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;

import com.sidarenka.alien.util.PasswordEncoder;
import com.sidarenka.alien.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User login(String login, String password) throws ServiceException {
        String encodedPassword = new String(PasswordEncoder.encodePassword(password));
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        User currentUser = new User();
        try {
            currentUser = userDaoImpl.findByLoginAndPassword(login, encodedPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return currentUser;
    }

    public List<User> registration(String login, String password, String email) throws ServiceException {
        if (!UserValidator.validateUserData(login, password, email)) {
            throw new ServiceException("Incorrect registration data");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<User> users = new ArrayList<>();
        String encodedPassword = new String(PasswordEncoder.encodePassword(password));
        try {
            if (!userDaoImpl.findByLogin(login)) {
                User user = new User();
                user.setLogin(login);
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

    public List<User> selectAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<User> users = new ArrayList<>();
        try {
            users = userDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    public void changeStatus( String selectedStatus,String selectedUser) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        try {
            userDaoImpl.updateUserStatus(selectedStatus,selectedUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public long takeUserId( String login) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        long userId = 0;
        try {
            User user =userDaoImpl.findByLoginForReview(login);
            userId = user.getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userId;
    }

    public String logOut() {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }


    public String goToRegistrationPage() {
        String page = ConfigurationManager.getProperty("path.page.registration-page");
        return page;
    }


    public String goToManePage() {

        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
