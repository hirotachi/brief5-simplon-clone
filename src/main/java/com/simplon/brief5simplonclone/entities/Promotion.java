package com.simplon.brief5simplonclone.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;

//create table if not exists promotions
//    (
//    id         serial primary key not null,
//    name       varchar(255)       not null,
//    year       integer            not null default 2019,
//    created_at timestamp          not null default now(),
//    updated_at timestamp          not null default now(),
//    deleted_at timestamp,
//    user_id    integer references users (id) on delete set null on update cascade -- formatteur
//    )
@Entity
@Table(name = "promotions")
public class Promotion {

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
  @Column(name = "year", nullable = false)
  private Integer year;


  //  Relations =========================================
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User teacher;

  @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
  private HashSet<Brief> briefs;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinTable(name = "users_promotions",
      joinColumns = {@JoinColumn(name = "promotion_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private HashSet<User> students;

//  Setters ================================================

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public void setBriefs(HashSet<Brief> briefs) {
    this.briefs = briefs;
  }

  public void setStudents(HashSet<User> students) {
    this.students = students;
  }

  //  Getters ================================================
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

  public Integer getYear() {
    return year;
  }

  public User getTeacher() {
    return teacher;
  }

  public HashSet<Brief> getBriefs() {
    return briefs;
  }

  public HashSet<User> getStudents() {
    return students;
  }
}
