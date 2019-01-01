package com.sidarenka.alien.controller;

import com.sidarenka.alien.entity.RoleType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleRedirectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
public void doFilter(ServletRequest request, ServletResponse response,    FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        RoleType type = (RoleType) session.getAttribute("roleType");
        if (type==RoleType.USER){
             session.setAttribute("userType", type);
             RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/main.jsp");
             dispatcher.forward(req, resp);
             return;  }    // pass the request along the filter

    }

    @Override
    public void destroy() {

    }
}
