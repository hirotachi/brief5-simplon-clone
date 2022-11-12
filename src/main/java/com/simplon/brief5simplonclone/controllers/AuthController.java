package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller()
public class AuthController {

  @Inject
  private Service<User> userService;


  @Handler(path = "/login")
  public void login(Response response, HttpSession session)
      throws IOException, ServletException {

//     check if user is logged in and redirect to home if he is

    response.render("login", new HashMap<>(Map.of("name", "working")));
  }

  @Handler(path = "/register", method = Methods.POST)
  public void register() {
    System.out.println("register page");
  }
}
