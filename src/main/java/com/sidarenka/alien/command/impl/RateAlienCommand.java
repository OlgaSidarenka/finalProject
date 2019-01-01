package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.CommonService;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class RateAlienCommand implements Command {
    private static final String PARAM_ALIEN_MARK = "rating";
    private static final String PARAM_ALIEN_ID = "alienId";
    private static final String PARAM_USER_LOGIN = "login";
    private CommonService commonService = new CommonService();
    private UserService userService = new UserService();
    private AlienService alienService = new AlienService();

    @Override
    public String execute(HttpServletRequest request) {
        int mark = Integer.parseInt(request.getParameter(PARAM_ALIEN_MARK));
        long alienId = Long.parseLong(request.getParameter(PARAM_ALIEN_ID));
        String login = request.getParameter(PARAM_USER_LOGIN);
        List<Alien> aliens = new ArrayList<>();
        try {
            long userId = userService.takeUserId(login);
            commonService.rateAlien(userId, alienId, mark);
            aliens = alienService.selectAll();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute("aliens", aliens);
        String page = ConfigurationManager.getProperty("path.page.main-page");
        return page;
    }
}
