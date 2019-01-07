package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class AddReviewCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static final String PARAM_REVIEW = "textReview";
    private static final String PARAM_ALIEN_NAME = "alienName";
    private static final String PARAM_ALIEN_ID = "alienId";
    private static final String PARAM_USER_LOGIN = "login";
    AlienService alienService = new AlienService();
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Alien alien = new Alien();
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
            request.setAttribute("alien", alien);
            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
