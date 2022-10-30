package com.simplon.brief5simplonclone.servlets;

import com.simplon.brief5simplonclone.controllers.Controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/*")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "nice world nice";
        Controllers.load();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controllers.handle(req, resp);
    }

    public void destroy() {
    }
}