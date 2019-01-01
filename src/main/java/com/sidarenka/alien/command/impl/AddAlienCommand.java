package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.CommandException;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class AddAlienCommand implements Command {
    private static final String PARAM_NAME_ALIEN_NAME = "alienName";
    private static final String PARAM_NAME_ALIEN_HOMELAND = "alienHomeland";
    private static final String PARAM_NAME_ALIEN_DESCRIPTION = "alienDescription";
    private AlienService alienService = new AlienService();

    @Override
    public String execute(HttpServletRequest request) {


        String alienName = request.getParameter(PARAM_NAME_ALIEN_NAME);
        String alienHomeland = request.getParameter(PARAM_NAME_ALIEN_HOMELAND);
        String alienDescription = request.getParameter(PARAM_NAME_ALIEN_DESCRIPTION);
        String page=null;
        try {
            List<Alien> aliens = alienService.createAlien(alienName, alienHomeland, alienDescription);
            if (aliens.isEmpty()) {
                //request.setAttribute("alien",aliens);
                page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
            }
           else{
                aliens=alienService.selectAll();
                request.setAttribute("aliens", aliens);
                page = ConfigurationManager.getProperty("path.page.admin-page");
            }
           // aliens = alienService.selectAll();
        }catch (ServiceException e){
            logger.log(Level.ERROR, e);
        }
       //TODO Massage if alien did not add
       // request.setAttribute("aliens", aliens);

        return page;
    }
}
