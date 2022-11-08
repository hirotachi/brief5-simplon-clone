package com.simplon.brief5simplonclone.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;

  @Column(name = "deleted_at")
  private Timestamp deletedAt;
//  Main ==============================================

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;


  @Column(name = "last_read_at", nullable = false)
  private Timestamp lastReadAt;

  @Column(name = "role", nullable = false)
  private Integer role;

  @Column(name = "banned", nullable = false)
  private boolean banned;

//  Relations ========================================

  @OneToMany(mappedBy = "teacher")
  private HashSet<Promotion> teachersPromotions;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Profile profile;

  @ManyToMany(mappedBy = "students")
  private HashSet<Promotion> studentsPromotions;

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

  public void setTeachersPromotions(HashSet<Promotion> teachersPromotions) {
    this.teachersPromotions = teachersPromotions;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public void setStudentsPromotions(HashSet<Promotion> studentsPromotions) {
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

  public HashSet<Promotion> getTeachersPromotions() {
    return teachersPromotions;
  }

  public Profile getProfile() {
    return profile;
  }

  public HashSet<Promotion> getStudentsPromotions() {
    return studentsPromotions;
  }
}
