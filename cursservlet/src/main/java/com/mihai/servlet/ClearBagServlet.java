package com.mihai.servlet;

import com.mihai.loginstate.UsersBag;
import com.mihai.util.SessionProperties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "clearBag", urlPatterns = "/clear")
public class ClearBagServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        String user = (String)session.getAttribute(SessionProperties.user);
        //PrintWriter out = resp.getWriter();
        String productToDelete = req.getHeader("prod");

        if(usersBag.userHasProductThenDelete(user, productToDelete)) // UsersBag.instance
            resp.setStatus(HttpServletResponse.SC_OK);
        else
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }
}
