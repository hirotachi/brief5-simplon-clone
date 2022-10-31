package com.simplon.brief5simplonclone.servlets;

import com.simplon.brief5simplonclone.core.Controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RootServlet", value = "/*")
public class RootServlet extends HttpServlet {
    public void init() {
        Controllers.load();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controllers.handle(req, resp);
    }
}
