package com.sidarenka.alien.command;

import javax.servlet.http.HttpServletRequest;
/**
 * The Interface Command.
 */
public interface Command {
    Router execute(HttpServletRequest request);
}
