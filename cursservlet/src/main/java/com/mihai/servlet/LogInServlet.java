package com.mihai.servlet;

import com.mihai.db.UserDB;
import com.mihai.loginstate.LogInUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "log-in", urlPatterns = "/attempt-login")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isUserValid = UserDB.instance.findUserByCredentials(username, password);

        System.out.println(isUserValid);

        if(isUserValid){
           // LogInUser.instance.setUserLogInState(true);
            session.setAttribute("login", "true");
            resp.sendRedirect("shop");
        }else{

        }

    }
}
