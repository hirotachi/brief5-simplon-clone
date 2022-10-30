package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Param;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller("/")
public class AuthController {
    @Handler(path = "/login/{id}", method = Methods.GET)
    public void login(@Param("id") String id, HttpServletResponse response) throws IOException {
        response.getWriter().println("login with id: " + id);
    }

    @Handler(path = "/register", method = Methods.POST)
    public void register() {
        System.out.println("register page");
    }
}
