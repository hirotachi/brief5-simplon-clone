package com.simplon.brief5simplonclone.entities;

import com.simplon.brief5simplonclone.annotations.Exclude;
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
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Basic
  @Column(name = "created_at")
  private Timestamp createdAt;

  @Basic
  @Column(name = "updated_at")
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
  @Exclude
  private String password;


  @Basic
  @Column(name = "last_read_at")
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

  @OneToMany(mappedBy = "teacher")
  private List<Brief> teacherBriefs;

  //  Setters ==========================================


  public User setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
    return this;
  }

  public User setName(String name) {
    this.name = name;
    return this;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public User setLastReadAt(Timestamp lastReadAt) {
    this.lastReadAt = lastReadAt;
    return this;
  }

  public User setRole(Integer role) {
    this.role = role;
    return this;
  }

  public User setBanned(boolean banned) {
    this.banned = banned;
    return this;
  }

  public User setTeachersPromotions(List<Promotion> teachersPromotions) {
    this.teachersPromotions = teachersPromotions;
    return this;
  }


  public User setStudentsPromotions(List<Promotion> studentsPromotions) {
    this.studentsPromotions = studentsPromotions;
    return this;
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

  
  public List<Brief> getTeacherBriefs() {
    return teacherBriefs;
  }
}
