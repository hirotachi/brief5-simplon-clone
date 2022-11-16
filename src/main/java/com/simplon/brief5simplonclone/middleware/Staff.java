package com.simplon.brief5simplonclone.middleware;

import com.simplon.brief5simplonclone.core.Middleware;
import com.simplon.brief5simplonclone.core.Response;
import com.simplon.brief5simplonclone.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class Staff implements Middleware {

  public boolean handle(HttpServletRequest request, Response response) throws IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() == 3) {
      response.status(403).send("Forbidden");
      return false;
    }
    return true;
  }

}
