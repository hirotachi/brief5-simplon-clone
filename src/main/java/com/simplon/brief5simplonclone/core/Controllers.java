package com.simplon.brief5simplonclone.core;


import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.controllers.AuthController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Controllers {

    private static final Class<?>[] controllers = new Class<?>[]{AuthController.class};
    private static final HashMap<Methods, HashMap<String, com.simplon.brief5simplonclone.core.Handler>> routesByMethod = new HashMap<>();

    public static void load() {
        for (Class<?> controller : controllers) {
            Controller annotation = controller.getAnnotation(Controller.class);
            if (annotation == null) {
                continue;
            }
            String controllerPath = normalizePath(annotation.value());
            Method[] methods = controller.getMethods();
            for (Method method : methods) {
                Handler handler = method.getAnnotation(Handler.class);
                if (handler == null) {
                    continue;
                }
                String path = normalizePath(handler.path());
                String fullPath = controllerPath + path;
                HashMap<String, com.simplon.brief5simplonclone.core.Handler> routes = routesByMethod.computeIfAbsent(handler.method(), k -> new HashMap<>());
                Class<? extends Middleware>[] middlewares = new Class[]{};
                com.simplon.brief5simplonclone.annotations.Middleware middlewareAn =
                        method.getAnnotation(com.simplon.brief5simplonclone.annotations.Middleware.class);
                if (middlewareAn != null) {
                    middlewares = middlewareAn.value();
                }
                routes.put(fullPath, new com.simplon.brief5simplonclone.core.Handler(method, fullPath, middlewares));
            }
        }

        System.out.println("Routes loaded");
        System.out.println(routesByMethod);

    }

    public static String normalizePath(String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
//        if path has something like {id} regexify it with the word inside the curly braces
        if (path.contains("{")) {
            int startIndex = path.indexOf("{");
            int endIndex = path.indexOf("}");
            String regex = path.substring(startIndex + 1, endIndex);
            path = path.replace("{" + regex + "}", "(?<" + regex + ">[^/]+)(\\?.*)?$");
        }
        return path;
    }

    public static void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        com.simplon.brief5simplonclone.core.Handler handler = getMethod(request);
        if (handler == null) {
//            todo: add default handler for errors
            response.sendError(404);
            return;
        }
        handler.run(request, response);
    }

    private static com.simplon.brief5simplonclone.core.Handler getMethod(HttpServletRequest request) {
        String path = normalizePath(request.getRequestURI());
        Methods method = Methods.valueOf(request.getMethod());
        HashMap<String, com.simplon.brief5simplonclone.core.Handler> routes = routesByMethod.get(method);
        com.simplon.brief5simplonclone.core.Handler handler = routes.get(path);
        if (handler == null) {
            for (String route : routes.keySet()) {
                if (path.matches(route) || path.equals(route)) {
                    handler = routes.get(route);
                }
            }
        }
        return handler;
    }

}
