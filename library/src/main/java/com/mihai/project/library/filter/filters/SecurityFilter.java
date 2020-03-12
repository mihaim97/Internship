package com.mihai.project.library.filter.filters;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username = httpServletRequest.getHeader("username");
        String password = httpServletRequest.getHeader("password");

     /*   if(username.equals("mihai")){
            System.out.println("Log in");
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
           httpServletResponse.sendError(403, "Invalid credentials");
        }*/
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
