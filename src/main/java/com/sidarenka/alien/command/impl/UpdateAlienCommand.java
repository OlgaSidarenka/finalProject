package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UpdateAlienCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static AlienService alienService = new AlienService();
    private static final String PARAM_ALIEN_NAME = "alienName";
    private static final String PARAM_ALIEN_DESCRIPTION = "newDescription";
    String page=null;
    @Override
    public String execute(HttpServletRequest request) {
        String alienName = request.getParameter(PARAM_ALIEN_NAME).trim();
        String alienDescription=request.getParameter(PARAM_ALIEN_DESCRIPTION).trim();
        List<Alien> aliens=new ArrayList<>();
        Alien alien=null;
        try{
            alienService.updateDescription(alienDescription,alienName);
            aliens=alienService.selectAll();
            request.setAttribute("aliens", aliens);
            page = ConfigurationManager.getProperty("path.page.admin-page");
        }
        catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }



}
