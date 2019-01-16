package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.USERS_PAGE_PATH;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowUsersCommand.
 */
public class ShowUsersCommand implements Command {
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();
    
    /** The Constant REQUEST_ATTRIBUTE_USERS. */
    private static final String REQUEST_ATTRIBUTE_USERS = "users";

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        try {
            List<User>users=userService.selectAll();
            request.setAttribute(REQUEST_ATTRIBUTE_USERS,users);
           page = ConfigurationManager.getProperty(USERS_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }
}
