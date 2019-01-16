package com.sidarenka.alien.controller;

import com.sidarenka.alien.command.CommandFactory;
import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.command.Router;
import com.sidarenka.alien.pool.ConnectionPool;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sidarenka.alien.command.JspPath.ERROR_PAGE_PATH;

/**
 * The Class Controller.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    
    /** The Constant MESSAGE_WRONG_COMMAND. */
    private static final String MESSAGE_WRONG_COMMAND = "Error command!";
    
    /** The Constant REQUEST_ATTRIBUTE_INFO. */
    private static final String REQUEST_ATTRIBUTE_INFO = "wrongInfoData";


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);
        Router router = command.execute(request);
        String nextPage = router.getPage();
        switch (router.getType()) {
            case FORWARD:
                request.getRequestDispatcher(nextPage).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(nextPage);
                break;
            default:
                response.sendRedirect(ERROR_PAGE_PATH);
                request.setAttribute(REQUEST_ATTRIBUTE_INFO, MESSAGE_WRONG_COMMAND);
        }
    }


    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
