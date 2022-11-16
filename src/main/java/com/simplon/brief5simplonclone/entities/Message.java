package com.simplon.brief5simplonclone.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;

//create table if not exists briefs_messages
//    (
//    id         serial primary key not null,
//    data       jsonb              not null default '{}'::jsonb,
//    created_at timestamp          not null default now(),
//    updated_at timestamp          not null default now(),
//    deleted_at timestamp,
//    user_id    integer references users (id) on delete set null on update cascade,
//    brief_id   integer references briefs (id) on delete set null on update cascade
//    );
@Entity
@Table(name = "briefs_messages")
public class Message extends BaseEntity {


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
  @Column(name = "data", nullable = false)
  private String data;

  //  Relations =========================================
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brief_id")
  private Brief brief;

//  Setters ==============================================


  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public void setData(String data) {
    this.data = data;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setBrief(Brief brief) {
    this.brief = brief;
  }
//  Getters ==============================================

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

  public String getData() {
    return data;
  }

  public User getUser() {
    return user;
  }

  public Brief getBrief() {
    return brief;
  }
}
