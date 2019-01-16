package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;

import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.REVIEWS_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

/**
 * The Class ShowReviewsCommand.
 */
public class ShowReviewsCommand implements Command {
    
    /** The Constant PARAM_ALIEN_ID. */
    private static final String PARAM_ALIEN_ID = "alienId";
    
    /** The Constant PARAM_ALIEN_NAME. */
    private static final String PARAM_ALIEN_NAME = "alienName";
    
    /** The Constant REQUEST_ATTRIBUTE_REVIEWS. */
    private static final String REQUEST_ATTRIBUTE_REVIEWS = "reviews";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIEN. */
    private static final String REQUEST_ATTRIBUTE_ALIEN = "alien";
    
    /** The alien service. */
    private AlienServiceImpl alienService = new AlienServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        long alienId = Long.parseLong(request.getParameter(PARAM_ALIEN_ID));
        String alienName = request.getParameter(PARAM_ALIEN_NAME);
        String page=null;
        Alien alien;
        try {
            alien = alienService.findAlienByName(alienName).get(0);
            List<Review> reviews = alienService.findReviews(alienId);
            request.setAttribute(REQUEST_ATTRIBUTE_ALIEN, alien);
            request.setAttribute(REQUEST_ATTRIBUTE_REVIEWS, reviews);
            page = ConfigurationManager.getProperty(REVIEWS_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }
}

