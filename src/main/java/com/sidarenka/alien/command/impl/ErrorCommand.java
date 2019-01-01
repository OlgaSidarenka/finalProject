package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.UserService;
import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    private UserService userService = new UserService();
    public String execute(HttpServletRequest request) {
        String message = MessageManager.getProperty("message.wrongcommand");
        request.setAttribute("wrongInfoData", message);
        return userService.goToManePage();
    }
}
