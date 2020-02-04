package com.mihai.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
        Cookie coockie = null;
       // Map<String, String> products;
        int accessTime = 0;

        resp.setContentType("text/html");
        out.println("<h1>Welcome to</h1>");
        out.println("<h2> " + getInitParameter("shopName") + "</h2>");

        synchronized (session){
            if(session.isNew()){
                out.print("You are new!");
                coockie = new Cookie("Bag", "Product");
                resp.addCookie(coockie);
            }else{
                accessTime = (int)session.getAttribute("key");
                accessTime++;
            }
            session.setAttribute("key", accessTime);
            out.println("Access time " + accessTime);

            if(req.getCookies()[0].getName().equals("Bag")){
                out.println("Your products " + req.getCookies()[0].getValue());
            }

        }





    }


}
