package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

import static com.sidarenka.alien.command.JspPath.INDEX_PAGE_PATH;

/**
 * The Class EmptyCommand.
 */
public class EmptyCommand implements Command {
    
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        Router router=new Router(page);
        return router;
    }
}
