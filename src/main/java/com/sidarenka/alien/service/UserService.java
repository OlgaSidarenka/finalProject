package com.sidarenka.alien.service;


import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.entity.User;


import java.util.List;

/**
 * The Interface UserService.
 */
public interface UserService {
   
   /**
    * Login.
    *
    * @param login the login
    * @param password the password
    * @return the user
    * @throws ServiceException the service exception
    */
   User login(String login, String password) throws ServiceException;

   /**
    * Registration.
    *
    * @param login the login
    * @param password the password
    * @param email the email
    * @return the list
    * @throws ServiceException the service exception
    */
   List<User> registration(String login, String password, String email) throws ServiceException;

    /**
     * Select all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> selectAll() throws ServiceException;

    /**
     * Change status.
     *
     * @param selectedStatus the selected status
     * @param selectedUser the selected user
     * @throws ServiceException the service exception
     */
    void changeStatus(String selectedStatus, String selectedUser) throws ServiceException;

   /**
    * Take user id.
    *
    * @param login the login
    * @return the long
    * @throws ServiceException the service exception
    */
   long takeUserId(String login) throws ServiceException;

    /**
     * Find user reviews.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Review> findUserReviews(long userId) throws ServiceException;

    /**
     * Block user review.
     *
     * @param reviewId the review id
     * @throws ServiceException the service exception
     */
    void blockUserReview(long reviewId) throws ServiceException;

    /**
     * Find rated aliens for user.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Alien> findRatedAliensForUser(long userId) throws ServiceException;
}
