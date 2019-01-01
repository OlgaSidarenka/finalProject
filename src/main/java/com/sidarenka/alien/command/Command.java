package com.sidarenka.alien.command;

import com.sidarenka.alien.dao.DaoException;
import com.sidarenka.alien.service.ServiceException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
