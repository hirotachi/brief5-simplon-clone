package com.simplon.brief5simplonclone.core;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Middleware {
    boolean handle(HttpServletRequest request, Response response) throws IOException;

    default boolean handle() {
        return true;
    }

}
