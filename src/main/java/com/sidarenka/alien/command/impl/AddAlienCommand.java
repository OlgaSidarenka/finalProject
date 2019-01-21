package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.ADD_ALIEN_FORM_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

public class AddAlienCommand implements Command {
    private static final String PARAM_ALIEN_NAME = "alienName";
    private static final String PARAM_ALIEN_HOMELAND = "alienHomeland";
    private static final String PARAM_ALIEN_DESCRIPTION = "alienDescription";
    private static final String REQUEST_ATTRIBUTE_HOMELAND = "homelands";
    private static final String REQUEST_ATTRIBUTE_ALIENS = "aliens";
    private static final String INFO_ATTRIBUTE = "infoData";
    private static final String MESSAGE_ALIEN_NOT_ADDED = "message.notAddAlien";
    private static final String MESSAGE_ALIEN_ADDED = "message.addAlien";
    private AlienServiceImpl alienService = new AlienServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String alienName = request.getParameter(PARAM_ALIEN_NAME);
        String alienHomeland = request.getParameter(PARAM_ALIEN_HOMELAND);
        String alienDescription = request.getParameter(PARAM_ALIEN_DESCRIPTION);
        String message;
        try {
            List<Alien> aliens = alienService.createAlien(alienName, alienHomeland, alienDescription);
            List<Homeland> homelands = alienService.showAllHomeland();
            if (aliens.isEmpty()) {
                message = MessageManager.getProperty(MESSAGE_ALIEN_NOT_ADDED);
                request.setAttribute(REQUEST_ATTRIBUTE_HOMELAND, homelands);
                request.setAttribute(INFO_ATTRIBUTE, message);
            } else {
                aliens = alienService.selectAll();
                message = MessageManager.getProperty(MESSAGE_ALIEN_ADDED);
                request.setAttribute(INFO_ATTRIBUTE, message);
                request.setAttribute(REQUEST_ATTRIBUTE_HOMELAND, homelands);
                request.setAttribute(REQUEST_ATTRIBUTE_ALIENS, aliens);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(ConfigurationManager.getProperty(ADD_ALIEN_FORM_PAGE_PATH));
        return router;
    }
}
