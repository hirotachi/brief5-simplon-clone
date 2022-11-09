package com.simplon.brief5simplonclone.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simplon.brief5simplonclone.annotations.Exclude;

public class JSON {

  private static Gson gson;

  public static Gson getGson() {
    if (gson == null) {
      ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
          return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
          return field.getAnnotation(Exclude.class) != null;
        }
      };
      gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
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
