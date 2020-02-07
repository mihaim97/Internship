package com.mihai.servlet;

import com.mihai.db.ProductsDB;
import com.mihai.util.Pages;
import com.mihai.util.SessionProprieties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="userBag", urlPatterns = "/yourBag")
public class UserBagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProprieties.user);

        req.setAttribute("products", ProductsDB.instance.getUserProducts(user));

        RequestDispatcher disp = req.getRequestDispatcher(Pages.userBag);
        disp.forward(req, resp);

    }
}
