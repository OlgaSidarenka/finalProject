package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.command.JspPath.ADMIN_REVIEWS_PAGE_PATH;
import static com.sidarenka.alien.dao.AbstractDao.logger;

/**
 * The Class BlockReviewCommand.
 */
public class BlockReviewCommand implements Command {
    
    /** The Constant PARAM_REVIEW_ID. */
    private static final String PARAM_REVIEW_ID = "reviewId";
    
    /** The Constant REQUEST_ATTRIBUTE_USER_ID. */
    private static final String REQUEST_ATTRIBUTE_USER_ID = "userId";
    
    /** The Constant REQUEST_ATTRIBUTE_REVIEWS. */
    private static final String REQUEST_ATTRIBUTE_REVIEWS = "reviews";
    
    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        long reviewId = Long.parseLong(request.getParameter(PARAM_REVIEW_ID));
        long userId = Long.parseLong(request.getParameter(REQUEST_ATTRIBUTE_USER_ID));
        try {
            userService.blockUserReview(reviewId);
          List<Review> reviews = userService.findUserReviews(userId);
            request.setAttribute(REQUEST_ATTRIBUTE_REVIEWS, reviews);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = ConfigurationManager.getProperty(ADMIN_REVIEWS_PAGE_PATH);
        Router router = new Router(page);
        return router;
    }
}
