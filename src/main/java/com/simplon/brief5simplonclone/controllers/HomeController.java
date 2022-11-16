package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Middleware;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.Brief;
import com.simplon.brief5simplonclone.entities.Promotion;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.middleware.Auth;
import com.simplon.brief5simplonclone.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

  @Inject
  Service<User> userService;

  @Inject
  Service<Promotion> promotionService;


  @Handler(path = "/")
  @Middleware(Auth.class)
  public void index(Response response, HttpSession session,
      @QueryParam(value = "section", defaultValue = "all") String section)
      throws IOException, ServletException {
    HashMap<String, Object> data = new HashMap<>();
    data.put("section", "section");

    User user = (User) session.getAttribute("user");
    int role = user.getRole();

//     refactoring
    if (role != 3) {
      HashMap<String, Object> userFilter = new HashMap<>();
      if (role == 2) {// show only students for teacher
        userFilter.put("role", 3);
      }
      if (role == 1 && !section.equals("all")) {
        int wantedRole = switch (section) {
          case "students" -> 3;
          case "teachers" -> 2;
          default -> 0;
        };
        userFilter.put("role", wantedRole);
      }
      List<User> users = userService.findBy(userFilter);
      List<Promotion> promotions = promotionService.getAll();
      data.put("promotions", promotions);
      data.put("members", users);
    }

    if (role == 1) {
      List<User> teachers = userService.findBy(new HashMap<>(Map.of("role", 2)));
      data.put("teachers", teachers);
    }

    if (role == 2) {
      List<Brief> briefs = user.getTeacherBriefs();
      data.put("briefs", briefs);
    }

    String view = switch (role) {
      case 1 -> "admin";
      case 2 -> "teacher";
      default -> "student";
    };
    response.render(view, data);
  }
}
