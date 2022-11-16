package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Middleware;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.Brief;
import com.simplon.brief5simplonclone.entities.Promotion;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.middleware.Staff;
import com.simplon.brief5simplonclone.services.Service;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Controller("/briefs")
public class BriefController {

  @Inject
  private Service<Brief> briefService;

  @Inject
  private Service<Promotion> promotionService;

  @Handler(path = "/", method = Methods.POST)
  @Middleware(Staff.class)
  public void create(Response response, HttpSession session, @QueryParam("name") String name,
      @QueryParam("description") String description,
      @QueryParam("promotion") int promotionId) throws IOException {
    Promotion promotion = promotionService.findById(promotionId);

    User user = (User) session.getAttribute("user");

    Brief brief = new Brief();
    brief.setName(name).setDescription(description).setPromotion(promotion).setTeacher(user);

    boolean save = briefService.save(brief);

    response.status(save ? 201 : 500).redirect("/");

  }

}
