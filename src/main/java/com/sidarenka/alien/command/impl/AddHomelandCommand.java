package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.entity.Homeland;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.impl.AlienServiceImpl;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.sidarenka.alien.command.JspPath.ADD_ALIEN_FORM_PAGE_PATH;


// TODO: Auto-generated Javadoc
/**
 * The Class AddHomelandCommand.
 */
public class AddHomelandCommand implements Command {
    
    /** The Constant REQUEST_ATTRIBUTE_HOMELAND. */
    private static final String REQUEST_ATTRIBUTE_HOMELAND = "homelands";
    
    /** The logger. */
    static Logger logger = LogManager.getLogger();
    
    /** The Constant PARAM_NEW_HOMELAND. */
    private static final String PARAM_NEW_HOMELAND = "newHomeland";
    
    /** The Constant INFO_ATTRIBUTE. */
    private static final String INFO_ATTRIBUTE = "infoDataHomeland";
    
    /** The Constant MESSAGE_HOMELAND_NOT_ADDED. */
    private static final String MESSAGE_HOMELAND_NOT_ADDED = "message.notAddHomeland";
    
    /** The Constant MESSAGE_HOMELAND_ADDED. */
    private static final String MESSAGE_HOMELAND_ADDED = "message.addHomeland";
    
    /** The alien service. */
    AlienServiceImpl alienService = new AlienServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        String message;
        try {
            String alienHomeland = request.getParameter(PARAM_NEW_HOMELAND);
            List<Homeland> homeland=alienService.addNewHomeland(alienHomeland);
            if(homeland.isEmpty()){
                message = MessageManager.getProperty(MESSAGE_HOMELAND_NOT_ADDED);
                request.setAttribute(INFO_ATTRIBUTE, message);
            }
            else {
                message = MessageManager.getProperty(MESSAGE_HOMELAND_ADDED);
                request.setAttribute(INFO_ATTRIBUTE, message);
            }
            List<Homeland> homelands = alienService.showAllHomeland();
            request.setAttribute(REQUEST_ATTRIBUTE_HOMELAND, homelands);
                page = ConfigurationManager.getProperty(ADD_ALIEN_FORM_PAGE_PATH);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Router router=new Router(page);
        return router;
    }
}
