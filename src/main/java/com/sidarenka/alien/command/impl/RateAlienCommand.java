package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.REVIEWS_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

/**
 * The Class RateAlienCommand.
 */
public class RateAlienCommand implements Command {
    
    /** The Constant PARAM_ALIEN_MARK. */
    private static final String PARAM_ALIEN_MARK = "rating";
    
    /** The Constant PARAM_ALIEN_ID. */
    private static final String PARAM_ALIEN_ID = "alienId";
    
    /** The Constant PARAM_USER_LOGIN. */
    private static final String PARAM_USER_LOGIN = "login";
    
    /** The Constant PARAM_ALIEN_NAME. */
    private static final String PARAM_ALIEN_NAME = "alienName";
    
    /** The Constant REQUEST_ATTRIBUTE_INFO_DATA. */
    private static final String REQUEST_ATTRIBUTE_INFO_DATA = "infoData";
    
    /** The Constant REQUEST_ATTRIBUTE_REVIEWS. */
    private static final String REQUEST_ATTRIBUTE_REVIEWS = "reviews";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIEN. */
    private static final String REQUEST_ATTRIBUTE_ALIEN = "alien";
    
    /** The Constant MESSAGE_RATE_ALIEN. */
    private static final String MESSAGE_RATE_ALIEN = "message.rateAlien";
       
       /** The user service. */
       private UserServiceImpl userService = new UserServiceImpl();
    
    /** The alien service. */
    private AlienServiceImpl alienService = new AlienServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        int mark = Integer.parseInt(request.getParameter(PARAM_ALIEN_MARK));
        long alienId = Long.parseLong(request.getParameter(PARAM_ALIEN_ID));
        String login = request.getParameter(PARAM_USER_LOGIN);
        Alien alien=new Alien();
        String message=null;
        try {
            List<Review> reviews = alienService.findReviews(alienId);
            request.setAttribute(REQUEST_ATTRIBUTE_REVIEWS, reviews);
            long userId = userService.takeUserId(login);
            Mark currentMark=alienService.rateAlien(userId, alienId, mark);
            if (currentMark.getMark()==0){
                  message = MessageManager.getProperty(MESSAGE_RATE_ALIEN);
                  request.setAttribute(REQUEST_ATTRIBUTE_INFO_DATA, message);
            }
            alien = alienService.findAlienByName(request.getParameter(PARAM_ALIEN_NAME)).get(0);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(REQUEST_ATTRIBUTE_ALIEN, alien);
        String page = ConfigurationManager.getProperty(REVIEWS_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}
