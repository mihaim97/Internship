package com.mihai.servlet;

import com.mihai.db.ProductsDB;
import com.mihai.loginstate.UsersBag;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "shop-servlet", urlPatterns = "/shop",
            initParams = {
                @WebInitParam(name="shopName", value = "End Shop")
            })
public class ShopServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    @Inject
    private ProductsDB productsDb;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String)session.getAttribute(SessionProperties.user);

        req.setAttribute("cars", productsDb.getProducts().get("Cars")); // ProductsDB.instance
        req.setAttribute("pc", productsDb.getProducts().get("PC")); // ProductsDB.instance

        if(usersBag.userExist(user)){ //UsersBag.instance
            req.setAttribute("userProducts", usersBag.countUserProducts(user)); //UsersBag.instance
        }

          RequestDispatcher disp = req.getRequestDispatcher(Pages.shop);
          disp.forward(req, resp);
    }


}
