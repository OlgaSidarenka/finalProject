package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.resource.ConfigurationManager;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.sidarenka.alien.command.JspPath.INDEX_PAGE_PATH;


/**
 * The Class ChangeLanguageCommand.
 */
public class ChangeLanguageCommand implements Command {
    
    /** The Constant LANGUAGE_PARAM. */
    private static final String LANGUAGE_PARAM = "language";
    
    /** The Constant SESSION_LOCALE_ATTR. */
    private static final String SESSION_LOCALE_ATTR = "language";

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String locale = request.getParameter(LANGUAGE_PARAM);
        session.setAttribute(SESSION_LOCALE_ATTR, locale);
        page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}
