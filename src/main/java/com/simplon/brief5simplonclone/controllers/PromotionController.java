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
import com.simplon.brief5simplonclone.services.Service;
import java.io.IOException;

@Controller("/promotions")
public class PromotionController {

  @Inject
  private Service<Promotion> promotionService;

  @Inject
  private Service<User> userService;

  @Handler(path = "/", method = Methods.POST)
  @Middleware(Admin.class)
  public void create(@QueryParam("name") String name, @QueryParam("year") int year,
      Response response) throws IOException {
    Promotion promotion = new Promotion();
    promotion.setName(name).setYear(year);

    boolean save = promotionService.save(promotion);
    response.status(save ? 201 : 500).redirect("/");
  }

  @Handler(path = "/{id}", method = Methods.DELETE)
  @Middleware(Admin.class)
  public void delete(@Param("id") int id) {
    System.out.println("delete promotion with id: " + id);
  }

  @Handler(path = "/{id}", method = Methods.POST)
  @Middleware(Admin.class)
  public void update(@Param("id") int id, @QueryParam("name") String name,
      @QueryParam("year") int year, @QueryParam("teacher") int teacherId, Response response)
      throws IOException {
    User teacher = userService.findById(teacherId);
    Promotion promotion = promotionService.findById(id);
    promotion.setName(name).setYear(year).setTeacher(teacher);
    boolean update = promotionService.update(promotion);
    response.status(update ? 200 : 500).redirect("/");

  }
}
