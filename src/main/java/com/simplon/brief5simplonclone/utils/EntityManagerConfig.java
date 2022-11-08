package com.simplon.brief5simplonclone.utils;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerConfig {

  private static final String PERSISTENCE_UNIT_NAME = "default";

  private static EntityManagerFactory emf = null;

  private static EntityManagerFactory getEntityManagerFactory() {
    if (emf == null) {
      emf = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return emf;
  }

  public static EntityManager getEntityManager() {
    return getEntityManagerFactory().createEntityManager();
  }
}
