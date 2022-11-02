package com.simplon.brief5simplonclone.middleware;

import com.simplon.brief5simplonclone.core.Middleware;
import com.simplon.brief5simplonclone.core.Response;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class Auth implements Middleware {

    public boolean handle(HttpServletRequest request, Response response) throws IOException {
        System.out.println("Auth middleware");
        return false;
    }
}
