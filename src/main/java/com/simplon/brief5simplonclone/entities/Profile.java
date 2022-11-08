package com.simplon.brief5simplonclone.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;

//create table if not exists profiles
//    (
//    id         serial primary key not null,
//    user_id    integer            not null references users (id) on delete cascade,
//    created_at timestamp          not null default now(),
//    updated_at timestamp          not null default now(),
//    deleted_at timestamp,
//    image      varchar(255)
//    );
@Entity
@Table(name = "profiles")
public class Profile {

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
  @Column(name = "image")
  private String image;


  //  Relations =========================================
  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

//  Setters =============================================

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setUser(User user) {
    this.user = user;
  }

//  Getters =============================================

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

  public String getImage() {
    return image;
  }

  public User getUser() {
    return user;
  }
}
