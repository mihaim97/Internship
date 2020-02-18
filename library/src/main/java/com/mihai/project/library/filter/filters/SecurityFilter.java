package com.mihai.project.library.filter.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String user = ((HttpServletResponse)servletResponse).getHeader("user");
        if(user != null) {
            if (user.equals("mihai"))
                System.out.println("Log in");
            else
                System.out.println("Fail");
        }
        System.out.println("Se filtreaza");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
