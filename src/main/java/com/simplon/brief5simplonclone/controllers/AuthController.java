package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.*;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.middleware.Admin;
import com.simplon.brief5simplonclone.middleware.Auth;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.HashMap;

@Controller()
public class AuthController {
    @Handler(path = "/login/{name}")
    @Middleware({Auth.class, Admin.class})
    public void login(Response response, @Param("name") String name) throws IOException, ServletException {
        response.render("login", new HashMap<>() {{
            put("name", name);
        }});
    }

    @Handler(path = "/register", method = Methods.POST)
    public void register() {
        System.out.println("register page");
    }
}
