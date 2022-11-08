package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Param;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.ServletException;
import java.io.IOException;

@Controller()
public class AuthController {


  @Handler(path = "/login/{name}")
  public void login(Response response, @Param("name") String name)
      throws IOException, ServletException {
    response.send("hello there " + name);
  }

  @Handler(path = "/register", method = Methods.POST)
  public void register() {
    System.out.println("register page");
  }
}
