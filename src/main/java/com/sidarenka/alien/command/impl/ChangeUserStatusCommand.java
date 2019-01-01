package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.CommandException;
import com.sidarenka.alien.entity.User;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class ChangeUserStatusCommand implements Command {
    private static final String PARAM_SELECTED_STATUS = "selectedStatus";
    private static final String PARAM_SELECTED_USER = "selectedUser";
    private UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) {
        String selectedStatus=request.getParameter(PARAM_SELECTED_STATUS);
        String selectedUser=request.getParameter(PARAM_SELECTED_USER);
        try{
            userService.changeStatus(selectedStatus,selectedUser);
            List<User>users=userService.selectAll();
            request.setAttribute("users",users);
        }catch (ServiceException e){
            logger.log(Level.ERROR, e);
        }
        String page = ConfigurationManager.getProperty("path.page.users-page");
        return page;
    }
}
