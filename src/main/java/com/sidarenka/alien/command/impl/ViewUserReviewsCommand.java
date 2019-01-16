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
 * The Class ViewUserReviewsCommand.
 */
public class ViewUserReviewsCommand implements Command {
    
    /** The Constant PARAM_USER_ID. */
    private static final String PARAM_USER_ID = "userId";
    
    /** The Constant REQUEST_ATTRIBUTE_REVIEWS. */
    private static final String REQUEST_ATTRIBUTE_REVIEWS = "reviews";
    
    /** The user service. */
    private UserServiceImpl userService = new UserServiceImpl();


    /* (non-Javadoc)
     * @see com.sidarenka.alien.command.Command#execute(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Router execute(HttpServletRequest request) {

        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));
        String page=null;
        try {
            List<Review> reviews = userService.findUserReviews(userId);
            request.setAttribute(REQUEST_ATTRIBUTE_REVIEWS, reviews);
            page = ConfigurationManager.getProperty(ADMIN_REVIEWS_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }
}