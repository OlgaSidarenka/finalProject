package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.RoleType;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowAlienByNameCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static AlienService alienService = new AlienService();
    private static final String PARAM_ALIEN_NAME = "alienName";
    String page=null;
    @Override
    public String execute(HttpServletRequest request) {
        String alienName = request.getParameter(PARAM_ALIEN_NAME).trim();
        List<Alien> aliens=new ArrayList<>();
        HttpSession session = request.getSession();
        RoleType userType= (RoleType) session.getAttribute("userRole");
        try{
            aliens=alienService.findAliensByNameFragment(alienName);
             request.setAttribute("aliens", aliens);
             page = userType==RoleType.ADMIN?ConfigurationManager.getProperty("path.page.admin-page"):ConfigurationManager.getProperty("path.page.main-page");
        }
        catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
