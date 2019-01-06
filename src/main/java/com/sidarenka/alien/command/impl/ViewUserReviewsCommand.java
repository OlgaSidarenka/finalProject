package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.CommonService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class ViewUserReviewsCommand implements Command {
    private static final String PARAM_USER_ID = "userId";

    private CommonService commonService = new CommonService();
    String page;

    @Override
    public String execute(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));

        try {
            List<Review> reviews = commonService.findUserReviews(userId);


            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return "/jsp/admin/userReviews.jsp";
    }
}