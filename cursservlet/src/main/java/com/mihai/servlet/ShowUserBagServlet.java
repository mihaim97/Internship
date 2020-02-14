package com.mihai.servlet;

import com.mihai.ejb.Database;
import com.mihai.ejb.DatabaseService;
import com.mihai.hibernate.entity.Order;
import com.mihai.hibernate.entity.User;
import com.mihai.loginstate.UsersBag;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.qualifier.JDBCDatabaseService;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userBagView", urlPatterns = "/showBag")
public class ShowUserBagServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    @Inject
    @JDBCDatabaseService
    private DatabaseService db;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProperties.user);

        req.setAttribute("products", usersBag.getUserProducts(user)); // UsersBag.instance

        List<Order> orderList = db.getUserOrders(user);

        req.setAttribute("orders", orderList);

        RequestDispatcher disp = req.getRequestDispatcher(Pages.userBag);
        disp.forward(req, resp);
    }
}
