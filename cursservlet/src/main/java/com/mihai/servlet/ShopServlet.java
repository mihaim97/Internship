package com.mihai.servlet;

import com.mihai.db.ProductsDB;
import com.mihai.ejb.Database;
import com.mihai.ejb.DatabaseService;
import com.mihai.hibernate.entity.Product;
import com.mihai.loginstate.UsersBag;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.qualifier.JDBCDatabaseService;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@WebServlet(name = "shop-servlet", urlPatterns = "/shop",
            initParams = {
                @WebInitParam(name="shopName", value = "End Shop")
            })
public class ShopServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    @Inject
    @JDBCDatabaseService
    private DatabaseService db;

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

        List<Product> productList = db.queryProducts();

        req.setAttribute("cars", productList.stream().
                                filter(p->p.getType().getType().equals("CAR")).collect(Collectors.toList()));

        req.setAttribute("pc", productList.stream().
                filter(p->p.getType().getType().equals("PC")).collect(Collectors.toList()));

        if(usersBag.userExist(user)){ //UsersBag.instance
            req.setAttribute("userProducts", usersBag.countUserProducts(user)); //UsersBag.instance
        }

          RequestDispatcher disp = req.getRequestDispatcher(Pages.shop);
          disp.forward(req, resp);
    }


}
