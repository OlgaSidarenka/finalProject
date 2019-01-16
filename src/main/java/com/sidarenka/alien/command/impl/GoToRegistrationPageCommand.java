package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

import static com.sidarenka.alien.command.JspPath.REGISTRATION_PAGE_PATH;

/**
 * The Class GoToRegistrationPageCommand.
 */
public class GoToRegistrationPageCommand implements Command {
    
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}

