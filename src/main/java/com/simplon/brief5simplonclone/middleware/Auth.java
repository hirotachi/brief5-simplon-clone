package com.simplon.brief5simplonclone.middleware;

import com.simplon.brief5simplonclone.core.Middleware;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class Auth implements Middleware {

  public boolean handle(HttpServletRequest request, Response response) throws IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("user") == null) {
      response.redirect("/login");
      return false;
    }
    return true;
  }
}
