package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;

import java.io.IOException;

@Controller()
public class AuthController {
    @Handler(path = "/login/{id}")
    public void login(Response response, @QueryParam("id") String id) throws IOException {
        response.send("hello world " + id);
    }

    @Handler(path = "/register", method = Methods.POST)
    public void register() {
        System.out.println("register page");
    }
}
