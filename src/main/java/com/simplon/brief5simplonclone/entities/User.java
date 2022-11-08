package com.simplon.brief5simplonclone.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

//create table if not exists users
//    (
//    id           serial primary key not null,
//    name         varchar(255)       not null,
//    email        varchar(255)       not null unique check (email ~* '^.+@.+\..+$'),
//    password     varchar(255)       not null,
//    created_at   timestamp          not null default now(),
//    updated_at   timestamp          not null default now(),
//    deleted_at   timestamp,
//    last_read_at timestamp          not null default now(),
//    role         integer            not null default 3, -- 1: admin, 2: formatteur, 3: apprenant
//    banned       boolean            not null default false
//    );
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Basic
  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Basic
  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;

  @Basic
  @Column(name = "deleted_at")
  private Timestamp deletedAt;
//  Main ==============================================

  @Basic
  @Column(name = "name", nullable = false)
  private String name;

  @Basic
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Basic
  @Column(name = "password", nullable = false)
  private transient String password;


  @Basic
  @Column(name = "last_read_at", nullable = false)
  private Timestamp lastReadAt;

  @Basic
  @Column(name = "role", nullable = false)
  private Integer role;

  @Basic
  @Column(name = "banned", nullable = false)
  private boolean banned;

  @Basic
  @Column(name = "image")
  private String image;

//  Relations ========================================

  @OneToMany(mappedBy = "teacher")
  private List<Promotion> teachersPromotions;


  @ManyToMany(mappedBy = "students")
  private List<Promotion> studentsPromotions;

  //  Setters ==========================================


  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setLastReadAt(Timestamp lastReadAt) {
    this.lastReadAt = lastReadAt;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public void setBanned(boolean banned) {
    this.banned = banned;
  }

  public void setTeachersPromotions(List<Promotion> teachersPromotions) {
    this.teachersPromotions = teachersPromotions;
  }


  public void setStudentsPromotions(List<Promotion> studentsPromotions) {
    this.studentsPromotions = studentsPromotions;
  }

  //  Getters ==========================================


  public Long getId() {
    return id;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public Timestamp getDeletedAt() {
    return deletedAt;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Timestamp getLastReadAt() {
    return lastReadAt;
  }

  public Integer getRole() {
    return role;
  }

  public boolean isBanned() {
    return banned;
  }

  public List<Promotion> getTeachersPromotions() {
    return teachersPromotions;
  }


  public List<Promotion> getStudentsPromotions() {
    return studentsPromotions;
  }
}
