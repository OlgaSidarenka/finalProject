package com.sidarenka.alien.service;

import com.sidarenka.alien.dao.DaoFactory;
import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.impl.AlienDaoImpl;
import com.sidarenka.alien.dao.impl.UserDaoImpl;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.validator.AlienValidator;
import com.sidarenka.alien.validator.ReviewValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlienService {
    public List<Alien> selectAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens = new ArrayList<>();
        try {
            aliens = alienDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }

    public List<Review> findReviews(long alienId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = alienDaoImpl.findAlienReviews(alienId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    public Alien defineAlien(long alienId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        Alien alien = new Alien();
        try {
            alienDaoImpl.findById(alienId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return alien;
    }

    public Alien findAlienByName(String alienName) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        Alien alien = new Alien();
        try {
            alien = alienDaoImpl.findAlienInformationByName(alienName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return alien;
    }

    public Review addReview(String textReview, long alienId, long userId) throws ServiceException {
        if (!ReviewValidator.validateReviewData(textReview)) {
            throw new ServiceException("Incorrect review data");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        Review currentReview = new Review();
        try {
            currentReview.setUserId(userId);
            currentReview.setAlienId(alienId);
            currentReview.setTextReview(textReview);
            currentReview.setDateReview(Date.valueOf(LocalDate.now()));
            alienDaoImpl.createReview(currentReview);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return currentReview;
    }

    public void addNewHomeland(String homelandName) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        try {
            List<Homeland> homelands = alienDaoImpl.findHomelandByName(homelandName);
            if (homelands.isEmpty()) {
                alienDaoImpl.createHomeland(new Homeland(homelandName));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Alien> createAlien(String alienName, String homelandName, String alienDescription) throws ServiceException {
        if (!AlienValidator.validateAlienData(alienName, homelandName, alienDescription)) {
            throw new ServiceException("Incorrect data for new Alien");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens = new ArrayList<>();
        try {
            addNewHomeland(homelandName);
            List<Homeland> homelands = alienDaoImpl.findHomelandByName(homelandName);
            if (!alienDaoImpl.findAlienByName(alienName)) {
                Alien alien = new Alien();
                alien.setAlienName(alienName);
                alienDaoImpl.findHomelandByName(homelandName);
                Homeland homeland = new Homeland(homelands.get(0).getHomelandId(), homelands.get(0).getHomelandName());
                alien.setHomeland(homeland);
                alien.setDescription(alienDescription);
                alienDaoImpl.create(alien);
                aliens.add(alien);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }

    public Mark addMark(long alienId, long userId, int mark) throws ServiceException {
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

    public void updateDescription(String alienDescription, String alienName) throws ServiceException {
        if (!AlienValidator.validateAlienDescription(alienDescription)) {
            throw new ServiceException("Incorrect data for alien description");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDao = daoFactory.getAlienDaoImpl();

        try {
            alienDao.updateAlienDescription(alienDescription, alienName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}



