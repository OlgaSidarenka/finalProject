package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User findByLoginAndPassword(String login, String password) throws DaoException;
    boolean findByLogin(String login) throws DaoException;
    User findByLoginForReview(String login) throws DaoException;
    void updateUserStatus(String status, String login)throws DaoException;
    List<Review> findUserReviews(long userId) throws DaoException;
    void blockUserReview(long reviewId) throws DaoException;

}
