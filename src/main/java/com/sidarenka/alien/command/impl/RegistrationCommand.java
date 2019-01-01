package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RegistrationCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private String page;
    private String message;
    private UserService userService = new UserService();

    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);

        List<User> users = null;
        try {
            users = userService.registration(login, password, email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (!users.isEmpty()) {
            request.setAttribute("user", users);
            message = MessageManager.getProperty("message.registration");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.registration-page");
        } else {
            message = MessageManager.getProperty("message.registrationerror");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.registration-page");
        }
        return page;
    }
}


