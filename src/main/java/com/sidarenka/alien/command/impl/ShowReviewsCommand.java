package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;

import com.sidarenka.alien.command.CommandException;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class ShowReviewsCommand implements Command {
    private static final String PARAM_ALIEN_ID = "alienId";
    private AlienService alienService = new AlienService();
    String page;

    @Override
    public String execute(HttpServletRequest request) {
        long alienId = Long.parseLong(request.getParameter(PARAM_ALIEN_ID));
      //  Alien alien=alienService.defineAlien(alienId);
        try {
            List<Review> reviews = alienService.findReviews(alienId);
            request.setAttribute("alien", alienId);
            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        }catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
