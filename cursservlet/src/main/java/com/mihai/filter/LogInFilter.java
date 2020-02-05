package com.mihai.filter;


import com.mihai.loginstate.LogInUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = {"shop-servlet", "apiServlet"})
public class LogInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        if(!LogInUser.instance.getUserLogInState()){
            RequestDispatcher disp = servletRequest.getRequestDispatcher("login.jsp");
            disp.forward(servletRequest, servletResponse);
        }else{
            chain.doFilter(servletRequest, servletResponse);
        }
    }
}
