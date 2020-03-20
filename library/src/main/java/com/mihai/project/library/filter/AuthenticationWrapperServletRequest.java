package com.mihai.project.library.filter;

import com.mihai.project.library.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthenticationWrapperServletRequest extends HttpServletRequestWrapper {

    private User authenticateUser;

    public AuthenticationWrapperServletRequest(HttpServletRequest request, User user) {
        super(request);
        this.authenticateUser = user;
    }

    public User getAuthenticateUser(){
        return this.authenticateUser;
    }

}
