package com.mihai.project.library.filter.filters;


import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.filter.AuthenticationWrapperServletRequest;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.service.user.UserServiceImpl;
import com.mihai.project.library.util.UtilConstant;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class RegularSecurityFilter implements Filter {

    private UserService userService;

    private MessageBuilder messageBuilder;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        initDependency(servletRequest);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username = httpServletRequest.getHeader(UtilConstant.USERNAME_HEADER);
        String password = httpServletRequest.getHeader(UtilConstant.PASSWORD_HEADER);
        User user = userService.performLogIn(username, password);
        if (user != null) {
            AuthenticationWrapperServletRequest authenticationServlet = new AuthenticationWrapperServletRequest(httpServletRequest, user);
            filterChain.doFilter(authenticationServlet, servletResponse);
            return;
        }
        httpServletResponse.setContentType(UtilConstant.APPLICATION_JSON);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(messageBuilder.asJSON(ExceptionMessage.BAD_CREDENTIALS_REGULAR).getBytes());
    }

    private void initDependency(ServletRequest servletRequest) {
        if (userService == null && messageBuilder == null) {
            ServletContext servletContext = servletRequest.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webApplicationContext.getBean(UserServiceImpl.class);
            messageBuilder = webApplicationContext.getBean(MessageBuilder.class);
        }
    }
}
