package com.mihai.filter;


import com.mihai.loginstate.LogInUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"shop-servlet", "apiServlet"})
public class LogInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession(true);
        RequestDispatcher disp = servletRequest.getRequestDispatcher("login.jsp");
        /*if(!LogInUser.instance.getUserLogInState()){
            RequestDispatcher disp = servletRequest.getRequestDispatcher("login.jsp");
            disp.forward(servletRequest, servletResponse);
        }else{
            chain.doFilter(servletRequest, servletResponse);
        }*/

        if(session.isNew()){
            disp.forward(servletRequest, servletResponse);
        }else{
            if(!(null == session.getAttribute("login"))){
                if(session.getAttribute("login").equals("true")) {
                   chain.doFilter(servletRequest, servletResponse);
                }else{
                    disp.forward(servletRequest, servletResponse);
                }
            } else{
                disp.forward(servletRequest, servletResponse);
            }
        }

    }
}
