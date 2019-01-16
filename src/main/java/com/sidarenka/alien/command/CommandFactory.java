package com.sidarenka.alien.command;

import com.sidarenka.alien.command.impl.EmptyCommand;
import com.sidarenka.alien.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
/**
 * A factory for creating Command objects.
 */
public class CommandFactory {

    /** The Constant PARAM_COMMAND. */
   public static final String PARAM_COMMAND="command";
    /** The logger. */
   static Logger logger = LogManager.getLogger();

    /**
     * Define command.
     *
     * @param request the request
     * @return the command
     */
    public Command defineCommand(HttpServletRequest request) {
        Command currentCommand;
        String commandName = request.getParameter(PARAM_COMMAND).toUpperCase().replaceAll("-", "_");
        if (commandName == null || commandName.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            currentCommand = CommandMap.getInstance().get(CommandType.valueOf(commandName));
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute("wrongAction", commandName + MessageManager.getProperty("message.wrongaction"));
            currentCommand = new EmptyCommand();
        }
        return currentCommand;
    }
}
