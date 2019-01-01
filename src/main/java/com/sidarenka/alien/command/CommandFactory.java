package com.sidarenka.alien.command;

import com.sidarenka.alien.command.impl.EmptyCommand;
import com.sidarenka.alien.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    Logger logger = LogManager.getLogger();
    public Command defineCommand(HttpServletRequest request) {
        Command currentCommand = new EmptyCommand();
        String commandName = request.getParameter("command").toUpperCase().replaceAll("-", "_");
        if (commandName == null || commandName.isEmpty()) {
            return currentCommand;
        }
        try {
            currentCommand = CommandMap.getInstance().get(CommandType.valueOf(commandName));
        } catch (IllegalArgumentException e) {
            //logger.
            request.setAttribute("wrongAction", commandName + MessageManager.getProperty("message.wrongaction"));

        }
        return currentCommand;
    }
}
