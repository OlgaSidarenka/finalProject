package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.entity.Homeland;

import java.util.List;

public interface AlienDao extends   AbstractDao<Alien> {
    Homeland createHomeland (Homeland homeland) throws DaoException;
    List<Homeland> findHomelandByName(String homelandName) throws DaoException;
    boolean findAlienByName(String alienName) throws DaoException;
}
