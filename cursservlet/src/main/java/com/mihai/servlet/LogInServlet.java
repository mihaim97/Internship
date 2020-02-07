package com.mihai.servlet;

import com.mihai.db.UserDB;
import com.mihai.ejb.Database;
import com.mihai.ejb.DatabaseImpl;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "log-in", urlPatterns = "/attempt-login")
public class LogInServlet extends HttpServlet {

    @Inject
    private Database db;

    @Inject
    private UserDB usersDB;

    private static Logger logger = LoggerFactory.getLogger(LogInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isUserValid = usersDB.findUserByCredentials(username, password); // UserDb.instance

        System.out.println(isUserValid);
        db.getMsg();

        if(isUserValid){
            logger.info("User: {} log in at {}", username, new Date().toString());
            session.setAttribute(SessionProperties.login, "true");
            session.setAttribute(SessionProperties.user, username);
            resp.sendRedirect("shop");
        }else{
            RequestDispatcher disp = req.getRequestDispatcher(Pages.login);
            disp.forward(req, resp);
        }

    }
}
