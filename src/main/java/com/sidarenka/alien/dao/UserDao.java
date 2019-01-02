package com.sidarenka.alien.dao;

import com.sidarenka.alien.entity.User;

public interface UserDao extends AbstractDao<User> {
    void updateUserStatus(String status,String login)throws DaoException;
}
