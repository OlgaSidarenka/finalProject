package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.RoleType;
import com.sidarenka.alien.entity.StatusType;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;


public class LogInCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private UserService userService = new UserService();
    private AlienService alienService = new AlienService();
    private String page;

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        User currentUser = null;

        try {
            currentUser = userService.login(login, password);
          } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (currentUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", currentUser);

            List<Alien> aliens = null;
            try {
                aliens = alienService.selectAll();
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
            request.setAttribute("aliens", aliens);
            definedPage(currentUser);
            String message = MessageManager.getProperty("message.blocked");
            request.setAttribute("wrongInfoData", message);
        } else {
            String message = MessageManager.getProperty("message.loginerror");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }

    private String definedPage(User currentUser) {
        if (currentUser.getUserRole() == RoleType.ADMIN) {
            page = ConfigurationManager.getProperty("path.page.admin-page");
        } else if (currentUser.getUserStatus() == StatusType.BLOCKED) {
              page = ConfigurationManager.getProperty("path.page.index");
        } else {
            page = ConfigurationManager.getProperty("path.page.main-page");
        }
        return page;
    }
}



