package com.simplon.brief5simplonclone.middleware;

import com.simplon.brief5simplonclone.core.Middleware;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UnAuthenticated implements Middleware {

  @Override
  public boolean handle(HttpServletRequest request, Response response) throws IOException {
    if (request.getSession().getAttribute("user") != null) {
      response.redirect("/");
      return false;
    }
    return true;
  }

}
