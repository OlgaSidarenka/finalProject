package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.ADD_ALIEN_FORM_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

/**
 * The Class FillNewAlienDataCommand.
 */
public class FillNewAlienDataCommand implements Command {
    
    /** The Constant REQUEST_ATTRIBUTE_HOMELANDS. */
    private static final String REQUEST_ATTRIBUTE_HOMELANDS = "homelands";
    
    /** The alien service. */
    private AlienServiceImpl alienService = new AlienServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        List<Homeland> homelands = new ArrayList<>();
        try {
            homelands = alienService.showAllHomeland();
        } catch (ServiceException e){
        logger.log(Level.ERROR, e);
    }
    request.setAttribute(REQUEST_ATTRIBUTE_HOMELANDS, homelands);
        String page = ConfigurationManager.getProperty(ADD_ALIEN_FORM_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}
