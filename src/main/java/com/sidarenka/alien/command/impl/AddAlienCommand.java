package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class AddAlienCommand implements Command {
    private static final String ALIEN_NAME = "alienName";
    private static final String ALIEN_HOMELAND = "alienHomeland";
    private static final String ALIEN_DESCRIPTION = "alienDescription";
    private AlienService alienService = new AlienService();

    @Override
    public String execute(HttpServletRequest request) {
        String alienName = request.getParameter(ALIEN_NAME);
        String alienHomeland = request.getParameter(ALIEN_HOMELAND);
        String alienDescription = request.getParameter(ALIEN_DESCRIPTION);
        String page = null;
        String message;
        try {
            List<Alien> aliens = alienService.createAlien(alienName, alienHomeland, alienDescription);
            if (aliens.isEmpty()) {
                message = MessageManager.getProperty("message.notAddAlien");
                request.setAttribute("infoData", message);
                page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
            } else {
                aliens = alienService.selectAll();
              //  message = MessageManager.getProperty("message.addAlien");
                //request.setAttribute("infoData", message);
                request.setAttribute("aliens", aliens);
                page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
            }
            // aliens = alienService.selectAll();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
