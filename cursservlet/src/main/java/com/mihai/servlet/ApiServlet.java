package com.mihai.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "apiServlet", urlPatterns = "/products")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        HashMap<String, String> map = new HashMap<>();
        map.put("P1", "Car");
        map.put("P2", "Car 2");
        map.put("P3", "Car 3");

        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        out.print(objectMapper.writeValueAsString(map));

    }
}
