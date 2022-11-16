package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Middleware;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.Promotion;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.middleware.Auth;
import com.simplon.brief5simplonclone.middleware.UnAuthenticated;
import com.simplon.brief5simplonclone.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller()
public class AuthController {

  @Inject
  private Service<User> userService;

  @Inject
  private Service<Promotion> promotionService;


  @Handler(path = "/login")
  @Middleware(UnAuthenticated.class)
  public void login(Response response)
      throws IOException, ServletException {
//    System.out.println(promotionService.getAll());
    response.render("login");
  }

  @Handler(path = "/login", method = Methods.POST)
  public void login(Response response, HttpSession session, @QueryParam("email") String email,
      @QueryParam("password") String password)
      throws IOException, ServletException {

    HashMap<String, Object> filter = new HashMap<>() {{
      put("email", email);
    }};

    User user = userService.findOne(filter);

    if (user != null && user.getPassword().equals(password)) {
      session.setAttribute("user", user);
      response.redirect("/");
    } else {
      response.redirect("/login");
    }
  }

  @Handler(path = "/register", method = Methods.POST)
  public void register() {
    System.out.println("register page");
  }


  @Middleware(Auth.class)
  @Handler(path = "/logout")
  public void logout(HttpSession session, Response response) throws IOException {
    session.invalidate();
    response.redirect("/");
  }
}
