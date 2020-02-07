package com.mihai.servlet;

import com.mihai.loginstate.UsersBag;
import com.mihai.util.SessionProprieties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "clearBag", urlPatterns = "/clear")
public class ClearBagServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProprieties.user);
        //PrintWriter out = resp.getWriter();
        String productToDelete = req.getHeader("prod");

        if(UsersBag.instance.userHasProductThenDelete(user, productToDelete))
            resp.setStatus(HttpServletResponse.SC_OK);
        else
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }
}
