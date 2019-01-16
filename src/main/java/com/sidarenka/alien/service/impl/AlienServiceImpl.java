package com.sidarenka.alien.service.impl;

import com.sidarenka.alien.dao.DaoFactory;
import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.dao.impl.AlienDaoImpl;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import com.sidarenka.alien.validator.AlienValidator;
import com.sidarenka.alien.validator.ReviewValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class AlienServiceImpl.
 */
public class AlienServiceImpl implements AlienService {
    
    /** The Constant WRONG_SYMBOL_REGEX. */
    private static final String WRONG_SYMBOL_REGEX = "[</>]";
    
    /** The Constant WRONG_SYMBOL_REPLACEMENT_REGEX. */
    private static final String WRONG_SYMBOL_REPLACEMENT_REGEX = "";
    
    /** The Constant INCORRECT_REVIEW_MESSAGE. */
    private static final String INCORRECT_REVIEW_MESSAGE = "Incorrect review data";
    
    /** The Constant INCORRECT_HOMELAND_NAME_MESSAGE. */
    private static final String INCORRECT_HOMELAND_NAME_MESSAGE = "Incorrect homeland name";
    
    /** The Constant INCORRECT_DESCRIPTION_MESSAGE. */
    private static final String INCORRECT_DESCRIPTION_MESSAGE = "Incorrect data for alien description";
    
    /** The Constant INCORRECT_DATA_FOR_NEW_ALIEN_MESSAGE. */
    private static final String INCORRECT_DATA_FOR_NEW_ALIEN_MESSAGE = "Incorrect data for new Alien";

    @Override
    public List<Alien> selectAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens;
        try {
            aliens = alienDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }
    
    @Override
    public List<Review> findReviews(long alienId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Review> reviews;
        try {
            reviews = alienDaoImpl.findAlienReviews(alienId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }
    
    @Override
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
    
    @Override
    public List<Alien> findAliensByNameFragment(String alienName) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens = new ArrayList<>();
        try {
            aliens = alienDaoImpl.findAliensInformationByNameFragment(alienName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }
    
    @Override
    public List<Alien> findAlienByName(String alienName) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Alien> aliens;
        try {
            aliens = alienDaoImpl.takeAlienInformationByName(alienName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aliens;
    }
    
    @Override
    public Review addReview(String inputReview, long alienId, long userId) throws ServiceException {
        String textReview = inputReview.replaceAll(WRONG_SYMBOL_REGEX, WRONG_SYMBOL_REPLACEMENT_REGEX);
        if (!ReviewValidator.validateReviewData(textReview)) {
            throw new ServiceException(INCORRECT_REVIEW_MESSAGE);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        Review currentReview = new Review();
        try {
            currentReview.setUserId(userId);
            currentReview.setAlienId(alienId);
            currentReview.setTextReview(textReview);
            currentReview.setUnblocked(true);
            currentReview.setDateReview(Date.valueOf(LocalDate.now()));
            alienDaoImpl.createReview(currentReview);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return currentReview;
    }
    
    @Override
    public List<Homeland> addNewHomeland(String newHomelandName) throws ServiceException {
        String homelandName = newHomelandName.replaceAll(WRONG_SYMBOL_REGEX, WRONG_SYMBOL_REPLACEMENT_REGEX);
        List<Homeland> homelands;
        List<Homeland> newHomeland = new ArrayList<>();
        if (!AlienValidator.validateAlienHomeland(homelandName)) {
            throw new ServiceException(INCORRECT_HOMELAND_NAME_MESSAGE);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        Homeland homeland;
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        try {
            homelands = alienDaoImpl.findHomelandByName(homelandName);
            if (homelands.isEmpty()) {
                homeland = alienDaoImpl.createHomeland(new Homeland(homelandName));
                newHomeland = Collections.singletonList(homeland);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newHomeland;
    }
    
    @Override
    public List<Homeland> showAllHomeland() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        AlienDaoImpl alienDaoImpl = daoFactory.getAlienDaoImpl();
        List<Homeland> homelands;
        try {
            homelands = alienDaoImpl.findAllHomelands();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return homelands;
    }

    @Override
    public List<Alien> createAlien(String inputName, String homelandName, String textDescription) throws ServiceException {
        String alienName = inputName.replaceAll(WRONG_SYMBOL_REGEX, WRONG_SYMBOL_REPLACEMENT_REGEX);
        String alienDescription = textDescription.replaceAll(WRONG_SYMBOL_REGEX, WRONG_SYMBOL_REPLACEMENT_REGEX);
        if (!AlienValidator.validateAlienData(alienName, homelandName, alienDescription)) {
            throw new ServiceException(INCORRECT_DATA_FOR_NEW_ALIEN_MESSAGE);
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
    
    @Override
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
    
    @Override
    public void updateDescription(String currentDescription, String alienName) throws ServiceException {
        String alienDescription = currentDescription.replaceAll(WRONG_SYMBOL_REGEX, WRONG_SYMBOL_REPLACEMENT_REGEX);
        if (!AlienValidator.validateAlienDescription(alienDescription)) {
            throw new ServiceException(INCORRECT_DESCRIPTION_MESSAGE);
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



