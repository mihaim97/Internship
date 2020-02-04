package com.mihai.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shop-servlet", urlPatterns = "/shop",
            initParams = {
                @WebInitParam(name="shopName", value = "End Shop")
            })
public class ShopServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        PrintWriter out = resp.getWriter();
        int accessTime = 0;

        out.println("<h1>Welcome to</h1>");
        out.println("<h2> " + getInitParameter("shopName") + "</h2>");

        if(session.isNew()){
            out.print("You are new!");
            session.setAttribute("key", accessTime);
        }else{
            accessTime = (int)session.getAttribute("key");
            accessTime++;
            session.setAttribute("key", accessTime);
        }

        out.println("Access time " + accessTime);

    }


}
