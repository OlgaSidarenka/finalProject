package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {
    private UserService userService = new UserService();
    public String execute(HttpServletRequest request) {
        return userService.goToRegistrationPage();
    }
}

