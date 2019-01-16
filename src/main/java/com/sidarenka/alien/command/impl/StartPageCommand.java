package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

import static com.sidarenka.alien.command.JspPath.INDEX_PAGE_PATH;

/**
 * The Class StartPageCommand.
 */
public class StartPageCommand implements Command {

    public Router execute(HttpServletRequest request) {
        String startPage=ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        Router router = new Router();
        router.setRedirect(startPage);
        return router;
    }
}

