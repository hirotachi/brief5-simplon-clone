package com.simplon.brief5simplonclone.utils;

import com.simplon.brief5simplonclone.annotations.Inject;
import com.simplon.brief5simplonclone.services.Service;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Dependency {

  private static final HashMap<String, Object> instances = new HashMap<>();

  public static <T> T getInstance(Class<T> clazz) {
    if (instances.containsKey(clazz.getName())) {
      return (T) instances.get(clazz.getName());
    }
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
        Type genericParameterType = constructor.getGenericParameterTypes()[i];
        if (clazz == paramType) {
          throw new RuntimeException("Circular dependency detected for class " + clazz.getName());
        }
        if (paramType == Service.class) {
          params[i] = getService(genericParameterType.toString());
          continue;
        }

        if (!paramType.isInterface()) {
          params[i] = getInstance(paramType);
        }
      }
      T instance = (T) constructor.newInstance(params);
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        if (field.isAnnotationPresent(Inject.class)) {
          field.setAccessible(true);
          if (field.getType() == Service.class) {
            field.set(instance, getService(field.getGenericType().toString()));
            continue;
          }
          field.set(instance, getInstance(field.getType()));
          field.setAccessible(false);
        }
      }
      instances.put(clazz.getName(), instance);
      return instance;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static Service<?> getService(String type) throws ClassNotFoundException {
    String genericParam = type.replaceAll(".*<(.*)>.*", "$1");
    Class<?> EntityName = Class.forName(genericParam);
    if (!instances.containsKey(EntityName.getName())) {
      instances.put(EntityName.getName(), new Service(EntityName));
    }
    return (Service<?>) instances.get(EntityName.getName());
  }

}
