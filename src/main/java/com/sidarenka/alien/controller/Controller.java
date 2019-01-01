package com.sidarenka.alien.controller;

import com.sidarenka.alien.command.CommandException;
import com.sidarenka.alien.command.CommandFactory;
import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
