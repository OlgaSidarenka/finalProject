package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.CommandException;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsersCommand implements Command {
    Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            List<User>users=userService.selectAll();
            request.setAttribute("users",users);
            page = ConfigurationManager.getProperty("path.page.users-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
