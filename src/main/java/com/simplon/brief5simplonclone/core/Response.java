package com.simplon.brief5simplonclone.core;

import com.simplon.brief5simplonclone.utils.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class Response {
    private final HttpServletResponse response;
    private final HttpServletRequest request;

    public Response(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
    }

    public void send(Object message) throws IOException {
        boolean isString = message instanceof String;
        if (!isString) {
            response.setContentType("application/json");
        }
        response.getWriter().println(isString ? message : JSON.stringify(message));
    }

    public void send(int status, String message) throws IOException {
        response.setStatus(status);
        response.getWriter().println(message);
    }

    public void send(int status) throws IOException {
        response.setStatus(status);
    }

    public void setHeader(String name, String value) {
        response.setHeader(name, value);
    }

    public void send(Object message, String contentType) throws IOException {
        response.setContentType(contentType);
        response.getWriter().println(JSON.stringify(message));
    }

    public void render(String view) throws IOException, ServletException {
        render(view, new HashMap<>());
    }

    public void render(String view, HashMap<String, Object> data) throws IOException, ServletException {
        request.getRequestDispatcher("pages/" + view + ".jsp").forward(request, response);
    }

    public void redirect(String path) throws IOException {
        response.sendRedirect(path);
    }

    public void redirect(int status, String path) throws IOException {
        response.setStatus(status);
        response.sendRedirect(path);
    }

    public void json(Object object) throws IOException {
        response.setContentType("application/json");
        response.getWriter().println(JSON.stringify(object));
    }

}
