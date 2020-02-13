package com.mihai.servlet;

import com.mihai.ejb.Database;
import com.mihai.loginstate.UsersBag;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "userBagView", urlPatterns = "/showBag")
public class ShowUserBagServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    @Inject
    @JDBCDatabase
    private Database db;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProperties.user);

        req.setAttribute("products", usersBag.getUserProducts(user)); // UsersBag.instance

        db.getUserOrders(user);

        RequestDispatcher disp = req.getRequestDispatcher(Pages.userBag);
        disp.forward(req, resp);
    }
}
