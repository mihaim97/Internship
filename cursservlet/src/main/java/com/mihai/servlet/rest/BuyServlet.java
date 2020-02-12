package com.mihai.servlet.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihai.loginstate.UsersBag;
import com.mihai.util.Pages;
import com.mihai.util.SessionProperties;
import com.mihai.util.URL;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "buy-servlet", urlPatterns = "/buy")
public class BuyServlet extends HttpServlet {

    @Inject
    private UsersBag usersBag;

    // Doar de dragul testului folosesc RestTemplate pentru a testa doPut...........

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = (String)req.getSession(false).getAttribute(SessionProperties.user);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(usersBag.getUserProducts(user));
        HttpEntity<String> entity = new HttpEntity<>(value);
        restTemplate.put(URL.ORDER_URL, entity);
        resp.sendRedirect(Pages.sendRedirectShop);

    }
}
