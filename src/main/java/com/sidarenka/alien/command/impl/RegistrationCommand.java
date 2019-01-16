package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.REGISTRATION_PAGE_PATH;

/**
 * The Class RegistrationCommand.
 */
public class RegistrationCommand implements Command {
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The Constant PARAM_NAME_LOGIN. */
    private static final String PARAM_NAME_LOGIN = "login";
    
    /** The Constant PARAM_NAME_PASSWORD. */
    private static final String PARAM_NAME_PASSWORD = "password";
    
    /** The Constant PARAM_NAME_EMAIL. */
    private static final String PARAM_NAME_EMAIL = "email";
    
    /** The Constant REQUEST_ATTRIBUTE_USER. */
    private static final String REQUEST_ATTRIBUTE_USER = "user";
    
    /** The Constant MESSAGE_REGISTRATION. */
    private static final String MESSAGE_REGISTRATION = "message.registration";
    
    /** The Constant MESSAGE_REGISTRATION_ERROR. */
    private static final String MESSAGE_REGISTRATION_ERROR = "message.registrationerror";
    
    /** The Constant REQUEST_ATTRIBUTE_INFO. */
    private static final String REQUEST_ATTRIBUTE_INFO = "wrongInfoData";

    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();

    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        HttpSession session = request.getSession();
        String page;
        String message;
        Router router = new Router();
        List<User> users;
        try {
            users = userService.registration(login, password, email);
            if (!users.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_USER, users);
                message = MessageManager.getProperty(MESSAGE_REGISTRATION);
                session.setAttribute(REQUEST_ATTRIBUTE_INFO, message);
                request.getSession().getAttribute(REQUEST_ATTRIBUTE_INFO);
                page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
                router.setForward(page);
            } else {
                message = MessageManager.getProperty(MESSAGE_REGISTRATION_ERROR);
                session.setAttribute(REQUEST_ATTRIBUTE_INFO, message);
                request.getSession().getAttribute(REQUEST_ATTRIBUTE_INFO);
                page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
                router.setForward(page);
            }
//            page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
//            router.setRedirect(page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
//            message = MessageManager.getProperty(MESSAGE_REGISTRATION_ERROR);
//            session.setAttribute(REQUEST_ATTRIBUTE_INFO, message);
//            page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
//            router.setRedirect(page);
        }
        return router;
    }
}


