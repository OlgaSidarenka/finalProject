package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.RoleType;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.sidarenka.alien.command.JspPath.ADMIN_PAGE_PATH;
import static com.sidarenka.alien.command.JspPath.MAIN_PAGE_PATH;

/**
 * The Class ShowRatedAliensCommand.
 */
public class ShowRatedAliensCommand implements Command {
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The Constant PARAM_USER_ID. */
    private static final String PARAM_USER_ID = "userId";
    
    /** The Constant SESSION_ATTRIBUTE_USER_ROLE. */
    private static final String SESSION_ATTRIBUTE_USER_ROLE = "userRole";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIENS. */
    private static final String REQUEST_ATTRIBUTE_ALIENS = "aliens";
    
    /** The user service. */
    UserServiceImpl userService=new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));
        System.out.println(userId);
        List<Alien> aliens;
        String page=null;
        HttpSession session = request.getSession();
        RoleType userType= (RoleType) session.getAttribute(SESSION_ATTRIBUTE_USER_ROLE);
        try {
            aliens=userService.findRatedAliensForUser(userId);
            request.setAttribute(REQUEST_ATTRIBUTE_ALIENS, aliens);
            page = userType==RoleType.ADMIN?ConfigurationManager.getProperty(ADMIN_PAGE_PATH):ConfigurationManager.getProperty(MAIN_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }
}
