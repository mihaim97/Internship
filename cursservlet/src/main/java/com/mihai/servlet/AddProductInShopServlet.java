package com.mihai.servlet;

import com.mihai.db.ProductsDB;
import com.mihai.loginstate.UsersBag;
import com.mihai.util.SessionProprieties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addProd", urlPatterns = "/addProduct")
public class AddProductInShopServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProprieties.user);
        PrintWriter out = resp.getWriter();

        String addProduct = req.getHeader("prod");

        try{
            ProductsDB.instance.addCarProduct(addProduct);
        }catch(Exception e){
            e.printStackTrace();
        }

        out.println("Refresh your browser! :)");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
