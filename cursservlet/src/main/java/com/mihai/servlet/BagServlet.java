package com.mihai.servlet;

import com.mihai.ejb.Database;
import com.mihai.loginstate.UserBag;
import com.mihai.loginstate.UsersBag;
import com.mihai.util.Attribute;
import com.mihai.util.Pages;
import com.mihai.util.Parameter;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bag-servlet", urlPatterns = {"/add-product"})
public class BagServlet extends HttpServlet {

    private UserBag userBag;

    @Inject
    private UsersBag usersBag;

    @Inject
    private Database db;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = (String)req.getParameter(Parameter.product);
        String user = (String)(req.getSession().getAttribute(Attribute.user));

        db.getMsg();
        // remove - JDBC
        if(usersBag.userExist(user)) { // UsersBag.instance.
            usersBag.addInUserBag(user, product); //UsersBag.instance.
        }else{
            userBag = new UserBag();
            userBag.addProduct(product);
            usersBag.addNewUsersInBag(user, userBag); // UsersBag.instance.
        }
        // SF remove

        resp.sendRedirect(Pages.sendRedirectShop);
    }
}
