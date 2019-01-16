package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.RoleType;
import com.sidarenka.alien.entity.StatusType;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.*;
import static com.sidarenka.alien.dao.AbstractDao.logger;


/**
 * The Class LogInCommand.
 */
public class LogInCommand implements Command {
    
    /** The Constant PARAM_NAME_LOGIN. */
    private static final String PARAM_NAME_LOGIN = "login";
    
    /** The Constant PARAM_NAME_PASSWORD. */
    private static final String PARAM_NAME_PASSWORD = "password";
    
    /** The Constant SESSION_ATTRIBUTE_USER. */
    private static final String SESSION_ATTRIBUTE_USER = "user";
    
    /** The Constant SESSION_ATTRIBUTE_USER_ROLE. */
    private static final String SESSION_ATTRIBUTE_USER_ROLE = "userRole";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIENS. */
    private static final String REQUEST_ATTRIBUTE_ALIENS = "aliens";
    
    /** The Constant REQUEST_ATTRIBUTE_WRONG_INFO. */
    private static final String REQUEST_ATTRIBUTE_WRONG_INFO = "wrongInfoDataLogin";
    
    /** The Constant MESSAGE_USER_BLOCKED. */
    private static final String MESSAGE_USER_BLOCKED = "message.blocked";
    
    /** The Constant MESSAGE_LOGIN_ERROR. */
    private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
    
    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();
    
    /** The alien service. */
    private AlienServiceImpl alienService = new AlienServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        Router router = new Router();
        User currentUser;
        String page;
        HttpSession session;
        try {
            currentUser = userService.login(login, password);
            if (currentUser != null) {
                session = request.getSession();
                session.setAttribute(SESSION_ATTRIBUTE_USER, currentUser);
                RoleType userRole = currentUser.getUserRole();
                session.setAttribute(SESSION_ATTRIBUTE_USER_ROLE, userRole);
                List<Alien> aliens = alienService.selectAll();
                request.setAttribute(REQUEST_ATTRIBUTE_ALIENS, aliens);
                page = definedPage(currentUser);
                router.setPage(page);
                String message = MessageManager.getProperty(MESSAGE_USER_BLOCKED);
                request.setAttribute(REQUEST_ATTRIBUTE_WRONG_INFO, message);
            } else {
                String message = MessageManager.getProperty(MESSAGE_LOGIN_ERROR);
                request.setAttribute(REQUEST_ATTRIBUTE_WRONG_INFO, message);
                page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
                router.setPage(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    /**
     * Defined page.
     *
     * @param currentUser the current user
     * @return the string
     */
    private String definedPage(User currentUser) {
        String page;
        if (currentUser.getUserRole() == RoleType.ADMIN) {
            page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
        } else if (currentUser.getUserStatus() == StatusType.BLOCKED) {
            page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        } else {
            page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
        }
        return page;
    }
}



