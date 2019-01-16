package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.ADMIN_PAGE_PATH;

/**
 * The Class UpdateAlienCommand.
 */
public class UpdateAlienCommand implements Command {
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The alien service. */
    private static AlienServiceImpl alienService = new AlienServiceImpl();
    
    /** The Constant PARAM_ALIEN_NAME. */
    private static final String PARAM_ALIEN_NAME = "alienName";
    
    /** The Constant PARAM_ALIEN_DESCRIPTION. */
    private static final String PARAM_ALIEN_DESCRIPTION = "newDescription";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIENS. */
    private static final String REQUEST_ATTRIBUTE_ALIENS = "aliens";

    @Override
    public Router execute(HttpServletRequest request) {
        String alienName = request.getParameter(PARAM_ALIEN_NAME).trim();
        String alienDescription = request.getParameter(PARAM_ALIEN_DESCRIPTION).trim();
        String page = null;
        List<Alien> aliens;
        try {
            alienService.updateDescription(alienDescription, alienName);
            aliens = alienService.findAlienByName(alienName);//.selectAll();
            request.setAttribute(REQUEST_ATTRIBUTE_ALIENS, aliens);
            page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }


}
