package com.simplon.brief5simplonclone.core;


import com.simplon.brief5simplonclone.annotations.Controller;
import com.simplon.brief5simplonclone.annotations.Handler;
import com.simplon.brief5simplonclone.annotations.Methods;
import com.simplon.brief5simplonclone.controllers.AuthController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Controllers {

  private static final HashMap<Methods, HashMap<String, com.simplon.brief5simplonclone.core.Handler>> routesByMethod = new HashMap<>();

  public static void load() {
    Class<?>[] controllers;
    try {
      controllers = getClassesForPackage(AuthController.class.getPackage().getName());
    } catch (IOException | URISyntaxException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
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
        HashMap<String, com.simplon.brief5simplonclone.core.Handler> routes = routesByMethod.computeIfAbsent(
            handler.method(), k -> new HashMap<>());
        Class<? extends Middleware>[] middlewares = new Class[]{};
        com.simplon.brief5simplonclone.annotations.Middleware middlewareAn =
            method.getAnnotation(com.simplon.brief5simplonclone.annotations.Middleware.class);
        if (middlewareAn != null) {
          middlewares = middlewareAn.value();
        }
        routes.put(fullPath,
            new com.simplon.brief5simplonclone.core.Handler(method, fullPath, middlewares));
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

  public static void handle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
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
    HashMap<String, com.simplon.brief5simplonclone.core.Handler> routes = routesByMethod.get(
        method);
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

  private static Class<?>[] getClassesForPackage(String packageName)
      throws IOException, URISyntaxException, ClassNotFoundException {

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL packageURL;
    ArrayList<String> names = new ArrayList<String>();
    ;

    packageName = packageName.replace(".", "/");
    packageURL = classLoader.getResource(packageName);

    if (packageURL.getProtocol().equals("jar")) {
      String jarFileName;
      JarFile jf;
      Enumeration<JarEntry> jarEntries;
      String entryName;

      // build jar file name, then loop through zipped entries
      jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
      jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
      System.out.println(">" + jarFileName);
      jf = new JarFile(jarFileName);
      jarEntries = jf.entries();
      while (jarEntries.hasMoreElements()) {
        entryName = jarEntries.nextElement().getName();
        if (entryName.startsWith(packageName) && entryName.length() > packageName.length() + 5) {
          entryName = entryName.substring(packageName.length(), entryName.lastIndexOf('.'));
          names.add(entryName);
        }
      }

      // loop through files in classpath
    } else {
      URI uri = new URI(packageURL.toString());
      File folder = new File(uri.getPath());
      // won't work with path which contains blank (%20)
      File[] contenuti = folder.listFiles();
      String entryName;
      for (File actual : contenuti) {
        entryName = actual.getName();
        entryName = entryName.substring(0, entryName.lastIndexOf('.'));
        if (!entryName.endsWith("$1")) {
          names.add(entryName);
        }
      }
    }

    Class<?>[] classes = new Class[names.size()];
    for (int i = 0; i < names.size(); i++) {
      classes[i] = Class.forName(packageName.replace("/", ".") + "." + names.get(i));
    }
    return classes;
  }


}
