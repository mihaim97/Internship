package com.mihai.servlet;

import com.mihai.db.UserDB;
import com.mihai.loginstate.LogInUser;
import com.mihai.util.Pages;
import com.mihai.util.SessionProprieties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "log-in", urlPatterns = "/attempt-login")
public class LogInServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(LogInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isUserValid = UserDB.instance.findUserByCredentials(username, password);

        System.out.println(isUserValid);

        if(isUserValid){
            logger.info("User: {} log in at {}", username, new Date().toString());
            session.setAttribute(SessionProprieties.login, "true");
            session.setAttribute(SessionProprieties.user, username);
            resp.sendRedirect("shop");
        }else{
            RequestDispatcher disp = req.getRequestDispatcher(Pages.login);
            disp.forward(req, resp);
        }

    }
}
