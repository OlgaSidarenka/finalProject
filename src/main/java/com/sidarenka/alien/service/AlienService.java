package com.sidarenka.alien.service;

import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;

import java.util.List;


/**
 * The Interface AlienService.
 */
public interface AlienService {
    
    /**
     * Select all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Alien> selectAll() throws ServiceException;

    /**
     * Find reviews.
     *
     * @param alienId the alien id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Review> findReviews(long alienId) throws ServiceException;

    /**
     * Define alien.
     *
     * @param alienId the alien id
     * @return the alien
     * @throws ServiceException the service exception
     */
    Alien defineAlien(long alienId) throws ServiceException;

    /**
     * Find aliens by name fragment.
     *
     * @param alienName the alien name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Alien> findAliensByNameFragment(String alienName) throws ServiceException;

    /**
     * Find alien by name.
     *
     * @param alienName the alien name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Alien> findAlienByName(String alienName) throws ServiceException;

    /**
     * Adds the review.
     *
     * @param inputReview the input review
     * @param alienId the alien id
     * @param userId the user id
     * @return the review
     * @throws ServiceException the service exception
     */
    Review addReview(String inputReview, long alienId, long userId) throws ServiceException;

    /**
     * Adds the new homeland.
     *
     * @param newHomelandName the new homeland name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Homeland> addNewHomeland(String newHomelandName) throws ServiceException;

    /**
     * Show all homeland.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Homeland> showAllHomeland() throws ServiceException;

    /**
     * Creates the alien.
     *
     * @param inputName the input name
     * @param homelandName the homeland name
     * @param textDescription the text description
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Alien> createAlien(String inputName, String homelandName, String textDescription) throws ServiceException;

    /**
     * Rate alien.
     *
     * @param userId the user id
     * @param alienId the alien id
     * @param mark the mark
     * @return the mark
     * @throws ServiceException the service exception
     */
    Mark rateAlien(long userId, long alienId, int mark) throws ServiceException;

    ;

    /**
     * Update description.
     *
     * @param currentDescription the current description
     * @param alienName the alien name
     * @throws ServiceException the service exception
     */
    void updateDescription(String currentDescription, String alienName) throws ServiceException;
}
