package com.simplon.brief5simplonclone.controllers;

import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.annotations.Middleware;
import com.simplon.brief5simplonclone.annotations.Param;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.User;
import com.simplon.brief5simplonclone.middleware.Admin;
import com.simplon.brief5simplonclone.middleware.Staff;
import com.simplon.brief5simplonclone.services.Service;
import java.io.IOException;

@Controller("/users")
public class UserController {

  @Inject
  private Service<User> userService;

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

  @Handler(path = "/{id}", method = Methods.PUT)
  @Middleware(Staff.class)
  public void update(@Param("id") int id) {
    System.out.println("update user " + id);
  }


}
