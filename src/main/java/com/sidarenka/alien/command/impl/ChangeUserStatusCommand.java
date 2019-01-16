package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.USERS_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

/**
 * The Class ChangeUserStatusCommand.
 */
public class ChangeUserStatusCommand implements Command {
    
    /** The Constant PARAM_SELECTED_STATUS. */
    private static final String PARAM_SELECTED_STATUS = "selectedStatus";
    
    /** The Constant PARAM_SELECTED_USER. */
    private static final String PARAM_SELECTED_USER = "selectedUser";
    
    /** The Constant REQUEST_ATTRIBUTE_USERS_LIST. */
    private static final String REQUEST_ATTRIBUTE_USERS_LIST = "users";
    
    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();
    
    @Override
    public Router execute(HttpServletRequest request) {
        String selectedStatus=request.getParameter(PARAM_SELECTED_STATUS);
        String selectedUser=request.getParameter(PARAM_SELECTED_USER);
        try{
            userService.changeStatus(selectedStatus,selectedUser);
            List<User>users=userService.selectAll();
            request.setAttribute(REQUEST_ATTRIBUTE_USERS_LIST,users);
        }catch (ServiceException e){
            logger.log(Level.ERROR, e);
        }
        String page = ConfigurationManager.getProperty(USERS_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}
