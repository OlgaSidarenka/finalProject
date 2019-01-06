package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.Review;
import com.sidarenka.alien.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    void updateUserStatus(String status,String login)throws DaoException;
    List<Review> findUserReviews(long userId) throws DaoException;

}
