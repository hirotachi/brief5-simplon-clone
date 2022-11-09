package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Param;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.Brief;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.services.Service;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Controller()
public class AuthController {

  @Inject
  private Service<User> userService;

  @Inject
  private Service<Brief> briefService;


  @Handler(path = "/login/{name}")
  public void login(Response response, @Param("name") String name)
      throws IOException, ServletException {

    List<User> all = userService.getAll();
    System.out.println(all.get(0).getPassword());
    response.send(all);
  }

  @Handler(path = "/register", method = Methods.POST)
  public void register() {
    System.out.println("register page");
  }
}
