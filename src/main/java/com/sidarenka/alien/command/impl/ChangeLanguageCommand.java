package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.resource.ConfigurationManager;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;


public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "language";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String locale = request.getParameter(LANGUAGE_PARAM);
        session.setAttribute(LOCALE_ATTR, locale);
        page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        return page;
    }
}
