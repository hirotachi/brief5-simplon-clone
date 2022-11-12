package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.ServletException;
import java.io.IOException;

@Controller
public class HomeController {

  @Handler(path = "/")
  public void index(Response response) throws IOException, ServletException {
    response.render("admin");

  }
}
