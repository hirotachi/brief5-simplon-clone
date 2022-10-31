package com.simplon.brief5simplonclone.utils;

import com.google.gson.Gson;

public class JSON {
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String stringify(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T parse(String json, Class<T> classOfT) {
        return getGson().fromJson(json, classOfT);
    }
}
