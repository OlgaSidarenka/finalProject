package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.REVIEWS_PAGE_PATH;

/**
 * The Class AddReviewCommand.
 */
public class AddReviewCommand implements Command {
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The Constant PARAM_REVIEW. */
    private static final String PARAM_REVIEW = "textReview";
    
    /** The Constant PARAM_ALIEN_NAME. */
    private static final String PARAM_ALIEN_NAME = "alienName";
    
    /** The Constant PARAM_ALIEN_ID. */
    private static final String PARAM_ALIEN_ID = "alienId";
    
    /** The Constant PARAM_USER_LOGIN. */
    private static final String PARAM_USER_LOGIN = "login";
    
    /** The Constant REQUEST_ATTRIBUTE_REVIEWS. */
    private static final String REQUEST_ATTRIBUTE_REVIEWS = "reviews";
    
    /** The Constant REQUEST_ATTRIBUTE_ALIEN. */
    private static final String REQUEST_ATTRIBUTE_ALIEN = "alien";
    
    /** The alien service. */
    AlienServiceImpl alienService = new AlienServiceImpl();
    
    /** The user service. */
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Alien alien;
        List<Review> reviews = new ArrayList<>();
        try {
            String textReview = request.getParameter(PARAM_REVIEW);
            long alienId = Long.parseLong(request.getParameter(PARAM_ALIEN_ID));
            String login = request.getParameter(PARAM_USER_LOGIN);
            String alienName = request.getParameter(PARAM_ALIEN_NAME);
            alien = alienService.findAlienByName(alienName).get(0);
            long userId = userService.takeUserId(login);
            Review review = alienService.addReview(textReview, alienId, userId);
            reviews.add(review);
            reviews = alienService.findReviews(alienId);
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
