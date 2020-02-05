package com.mihai.servlet;

import com.mihai.db.UserDB;
import com.mihai.loginstate.LogInUser;
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
           // LogInUser.instance.setUserLogInState(true);
            logger.info("User: {} log in at {}", username, new Date().toString());
            session.setAttribute("login", "true");
            resp.sendRedirect("shop");
        }else{
            RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
            disp.forward(req, resp);
        }

    }
}
