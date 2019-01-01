package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
