package com.simplon.brief5simplonclone.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Dependency {


  public static <T> T getInstance(Class<T> clazz) {
    try {
      Constructor<?>[] constructors = clazz.getDeclaredConstructors();
      Constructor<?> constructor = null;
      for (Constructor<?> c : constructors) {
        if (c.getModifiers() == Modifier.PUBLIC) {
          constructor = c;
          break;
        }
      }
      if (constructor == null) {
        constructor = clazz.getDeclaredConstructor();
      }
      Object[] params = new Object[constructor.getParameterCount()];
      for (int i = 0; i < constructor.getParameterCount(); i++) {
        Class<?> paramType = constructor.getParameterTypes()[i];

        if (!paramType.isInterface() && paramType != Class.class) {
          params[i] = getInstance(paramType);
        }
      }
      T instance = (T) constructor.newInstance(params);
//      Field[] fields = clazz.getDeclaredFields();
//      for (Field field : fields) {
//        if (field.isAnnotationPresent(Inject.class)) {
//          field.setAccessible(true);
//          field.set(instance, getInstance(field.getType()));
//          field.setAccessible(false);
//        }
//      }
      return instance;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

}
