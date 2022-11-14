package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

  @Handler(path = "/")
  public void index(Response response,
      @QueryParam(value = "section", defaultValue = "all") String section)
      throws IOException, ServletException {
    response.render("admin", new HashMap<>(Map.of("section", section)));

  }
}
