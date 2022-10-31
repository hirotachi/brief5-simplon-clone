package com.simplon.brief5simplonclone.core;

import com.simplon.brief5simplonclone.annotations.Param;
import com.simplon.brief5simplonclone.annotations.QueryParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handler {
    private final Method method;
    private final String path;

    public Handler(Method method, String path) {
        this.method = method;
        this.path = path;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public void run(HttpServletRequest request, HttpServletResponse response) {
        Class<?> aClass = this.method.getDeclaringClass();
        try {

            Object instance = aClass.getConstructor().newInstance();
            Parameter[] parameters = this.method.getParameters();
            Object[] args = new Object[parameters.length];
            Pattern pattern = Pattern.compile(path);
            Matcher matcher = pattern.matcher(Controllers.normalizePath(request.getRequestURI()));
            if (!matcher.find()) return;


            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                Class<?> type = parameter.getType();

                if (type == HttpServletRequest.class) {
                    args[i] = request;
                } else if (type == HttpServletResponse.class) {
                    args[i] = response;
                } else if (type == Response.class) {
                    args[i] = new Response(response, request);

                } else {
                    Annotation[] annotations = parameter.getAnnotations();
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof Param) {
                            String name = ((Param) annotation).value();
                            Object value;
                            try {
                                value = matcher.group(name);
                            } catch (Exception e) {
                                response.sendError(500, "Invalid path parameter " + name + " for path " + path + " in method " + method.getName());
                                return;
                            }
                            args[i] = parse(value, type);
                            continue;
                        }
                        if (annotation instanceof jakarta.servlet.http.HttpSession) {
                            args[i] = request.getSession();
                            continue;
                        }
                        if (annotation instanceof jakarta.servlet.http.Cookie) {
                            args[i] = request.getCookies();
                            continue;
                        }
                        if (annotation instanceof QueryParam) {
                            QueryParam queryParam = (QueryParam) annotation;
                            String name = queryParam.value();
                            Object value = request.getParameter(name);
                            if (value == null) {
                                value = queryParam.defaultValue();
                            }

                            args[i] = parse(value, type);
                        }
                    }
                }
            }
            this.method.invoke(instance, args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object parse(Object value, Class<?> type) {
        if (value == null || value.toString().isEmpty()) {
            return switch (type.getSimpleName()) {
                case "int" -> 0;
                case "long" -> 0L;
                case "float" -> 0.0f;
                case "double" -> 0.0;
                case "boolean" -> false;
                default -> null;
            };
        }
        if (type == String.class) {
            return value;
        }
        if (type == Long.class) {
            return Long.parseLong((String) value);
        }
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt((String) value);
        }
        if (type == Float.class) {
            return Float.parseFloat((String) value);
        }
        if (type == Double.class) {
            return Double.parseDouble((String) value);
        }
        return null;
    }
}
