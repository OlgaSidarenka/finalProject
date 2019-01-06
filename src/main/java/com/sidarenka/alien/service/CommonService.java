package com.sidarenka.alien.service;

import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.DaoFactory;
import com.sidarenka.alien.dao.impl.AlienDaoImpl;
import com.sidarenka.alien.dao.impl.UserDaoImpl;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class CommonService {
    public Mark rateAlien(long userId, long alienId, int mark) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        Mark currentMark = new Mark();
        try {
            if (!alienDaoImpl.findMarkByUserIdAndAlienId(userId, alienId)) {
                currentMark.setUserId(userId);
                currentMark.setAlienId(alienId);
                currentMark.setMark(mark);
                alienDaoImpl.insertMark(currentMark);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return currentMark;
    }
    public List<Review> findUserReviews(long userId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDaoImpl userDaoImpl = daoFactory.getUserDaoImpl();
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = userDaoImpl.findUserReviews(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }
}
