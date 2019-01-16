package com.sidarenka.alien.filter;

import com.sidarenka.alien.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.sidarenka.alien.command.JspPath.*;


/**
 * The Class SessionSecurityFilter.
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE},
        urlPatterns = {"/jsp/*"}, servletNames = {"Controller"}, filterName = "sessionSecurityFilter")
public class SessionSecurityFilter implements Filter {
    
    /** The Constant registrationUri. */
    private static final String registrationUri="/jsp/user/registration.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if ((session == null || session.getAttribute("user") == null)&&(request.getRequestURI().intern()!=registrationUri)) {
            response.sendRedirect(ConfigurationManager.getProperty(INDEX_PAGE_PATH));
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
