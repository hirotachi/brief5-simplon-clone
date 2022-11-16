package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Middleware;
import com.simplon.brief5simplonclone.annotations.Param;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.Promotion;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.middleware.Admin;
import com.simplon.brief5simplonclone.middleware.Staff;
import com.simplon.brief5simplonclone.services.Service;
import java.io.IOException;
import java.util.List;

@Controller("/users")
public class UserController {

  @Inject
  private Service<User> userService;

  @Inject
  private Service<Promotion> promotionService;

  @Handler(path = "/", method = Methods.POST)
  @Middleware(Admin.class)
  public void create(Response response, @QueryParam("name") String name,
      @QueryParam("email") String email, @QueryParam("password") String password,
      @QueryParam("role") int role) throws IOException {
    // name/role/email/password from request body
    User user = new User();
    user.setName(name).setEmail(email).setPassword(password).setRole(role);

    boolean save = userService.save(user);

    response.status(save ? 201 : 500).redirect("/");

  }

  @Handler(path = "/{id}", method = Methods.DELETE)
  @Middleware(Admin.class)
  public void delete(@Param("id") int id) {
    // name/role/email/password from request body
    System.out.println("delete user with id: " + id);
  }

  @Handler(path = "/{id}")
  @Middleware(Staff.class)
  public void update(Response response, @Param("id") int id,
      @QueryParam("promotionId") int promotionId)
      throws IOException {
    User user = userService.findById(id);
    Promotion promotion = promotionService.findById(promotionId);
    List<Promotion> promotions = user.getStudentsPromotions();
    boolean exists = promotions.contains(promotion);
    if (!exists) {
      promotions.add(promotion);
    } else {
      promotions.remove(promotion);
    }
    user.setStudentsPromotions(promotions);
    boolean update = userService.update(user);

    response.status(update ? 200 : 500).redirect("/");

  }


}
