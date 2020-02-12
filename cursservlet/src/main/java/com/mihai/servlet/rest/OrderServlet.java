package com.mihai.servlet.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihai.ejb.Database;
import com.mihai.qualifier.JDBCDatabase;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "order-servlet", urlPatterns = "/order")
@ServletSecurity(httpMethodConstraints = {@HttpMethodConstraint(value = "PUT")})
public class OrderServlet extends HttpServlet {

    @Inject
    @JDBCDatabase
    private Database db;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Sa procesat comanda!!!!");
        ObjectMapper mapper = new ObjectMapper();
        String result = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        List<String> productInCurrentOrder = mapper.readValue(result, ArrayList.class);

        productInCurrentOrder.stream().forEach(p->System.out.println(p));
    }
}
