package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.entity.Mark;
import com.sidarenka.alien.entity.Review;

import java.util.List;

public interface AlienDao extends   AbstractDao<Alien> {
    List<Alien> findAliensInformationByNameFragment(String alienName) throws DaoException;

    List<Alien> takeAlienInformationByName(String alienName) throws DaoException;

    List<Review> findAlienReviews(long alienId) throws DaoException;

    List<Alien> findRatedAliensForUser(long userId) throws DaoException;

    void createReview(Review review) throws DaoException;

    Homeland createHomeland (Homeland homeland) throws DaoException;
    List<Homeland> findAllHomelands() throws DaoException;
    List<Homeland> findHomelandByName(String homelandName) throws DaoException;

    boolean findMarkByUserIdAndAlienId(long userId, long alienId) throws DaoException;

    boolean findAlienByName(String alienName) throws DaoException;

    void insertMark(Mark mark) throws DaoException;

    void updateAlienDescription(String alienDescription, String alienName) throws DaoException;
}
