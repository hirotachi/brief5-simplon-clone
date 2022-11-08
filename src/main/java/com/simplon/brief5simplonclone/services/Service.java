package com.simplon.brief5simplonclone.services;

import com.simplon.brief5simplonclone.utils.EntityManagerConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class Service<T> {


  final private Class<T> clazz;

  final private String entityName;

  public static EntityManager em = EntityManagerConfig.getEntityManager();

  public Service(Class<T> clazz) {
    this.clazz = clazz;
    this.entityName = clazz.getSimpleName();
  }

  public List<T> getAll(Integer limit, Integer offset) {
    TypedQuery<T> query = em.createQuery("SELECT e FROM " + this.entityName + " e", this.clazz);
    if (limit != null) {
      query.setMaxResults(limit);
    }
    if (offset != null) {
      query.setFirstResult(offset);
    }
    return query.getResultList();
  }

  public List<T> getAll() {
    return this.getAll(null, null);
  }

  public List<T> find(HashMap<String, Object> params, Integer limit, Integer offset) {
    StringBuilder query = new StringBuilder("SELECT e FROM " + this.entityName + " e WHERE ");
    for (String key : params.keySet()) {
      query.append("e.").append(key).append(" = :").append(key).append(" AND ");
    }
    query = new StringBuilder(query.substring(0, query.length() - 5));
    TypedQuery<T> typedQuery = em.createQuery(query.toString(), this.clazz);
    for (String key : params.keySet()) {
      typedQuery.setParameter(key, params.get(key));
    }
    if (limit != null) {
      typedQuery.setMaxResults(limit);
    }
    if (offset != null) {
      typedQuery.setFirstResult(offset);
    }
    return typedQuery.getResultList();
  }

  public List<T> find(HashMap<String, Object> params) {
    return this.find(params, null, null);
  }

  public T findOne(HashMap<String, Object> params) {
    List<T> result = this.find(params, 1, 0);
    if (result.size() > 0) {
      return result.get(0);
    }
    return null;
  }

  public T findById(Integer id) {
    return this.findOne(new HashMap<>() {{
      put("id", id);
    }});
  }

  public boolean save(T entity) {
    try {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean update(T entity) {
    try {
      em.getTransaction().begin();
      em.merge(entity);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean delete(T entity) {
    try {
      em.getTransaction().begin();
      em.remove(entity);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean softDelete(T entity) {
//     check if class has deletedAt attribute if not return false
    try {
      Field deletedAtField = clazz.getDeclaredField("deletedAt");
      if (deletedAtField == null || deletedAtField.getType() != Timestamp.class) {
        throw new Exception("Class does not have deletedAt attribute");
      }
      deletedAtField.setAccessible(true);
      deletedAtField.set(entity, new Timestamp(System.currentTimeMillis()));
      deletedAtField.setAccessible(false);

      em.getTransaction().begin();
      em.remove(entity);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
