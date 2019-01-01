package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FillNewAlienData implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
        return page;
    }
}
