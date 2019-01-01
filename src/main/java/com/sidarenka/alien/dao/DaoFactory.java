package com.sidarenka.alien.dao;

import com.sidarenka.alien.dao.impl.AlienDaoImpl;
import com.sidarenka.alien.dao.impl.UserDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();
    private final AlienDaoImpl alienDaoImpl = new AlienDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDaoImpl getUserDaoImpl() {
        return userDaoImpl;
    }

    public AlienDaoImpl getAlienDaoImpl() {
        return alienDaoImpl;
    }
}
