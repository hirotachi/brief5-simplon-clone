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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;


//create table if not exists briefs
//    (
//    id                   serial primary key not null,
//    name                 varchar(255)       not null,
//    created_at           timestamp          not null default now(),
//    updated_at           timestamp          not null default now(),
//    deleted_at           timestamp,
//    description          text,
//    context              text,
//    language             int                not null default 1,                                  -- 1: fr, 2: en
//    image                varchar(255),
//    technologies         text[],
//    frameworks           text[],
//    deliverables         text[],
//    assessment_methods   text[],
//    learning_methods     text[],
//    performance_criteria text,
//    deadline             timestamp,
//    skills               jsonb,
//    user_id              integer references users (id) on delete set null on update cascade,     -- formatteur
//    promotion_id         integer references promotions (id) on delete set null on update cascade -- promotion
//    );
@Entity
@Table(name = "briefs")
public class Brief {

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
  @Column(name = "description")
  private String description;

  @Basic
  @Column(name = "context")
  private String context;

  @Basic
  @Column(name = "language", nullable = false)
  private Integer language;

  @Basic
  @Column(name = "image")
  private String image;

  @Basic
  @Column(name = "technologies")
  private Object technologies;

  @Basic
  @Column(name = "frameworks")
  private Object frameworks;

  @Basic
  @Column(name = "deliverables")
  private Object deliverables;

  @Basic
  @Column(name = "assessment_methods")
  private Object assessmentMethods;

  @Basic
  @Column(name = "learning_methods")
  private Object learningMethods;

  @Basic
  @Column(name = "performance_criteria")
  private String performanceCriteria;

  @Basic
  @Column(name = "deadline")
  private Timestamp deadline;

  @Basic
  @Column(name = "skills")
  private String skills;

  //  Relations ========================================
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "user_id", nullable = false)
  private User teacher;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "promotion_id", nullable = false)
  private Promotion promotion;

  @OneToMany(mappedBy = "brief", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private List<Message> messages;

//  Setters ===========================================


  public Brief setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
    return this;
  }

  public Brief setName(String name) {
    this.name = name;
    return this;
  }

  public Brief setDescription(String description) {
    this.description = description;
    return this;
  }

  public Brief setContext(String context) {
    this.context = context;
    return this;
  }

  public Brief setLanguage(Integer language) {
    this.language = language;
    return this;
  }

  public Brief setImage(String image) {
    this.image = image;
    return this;
  }

  public Brief setTechnologies(Object technologies) {
    this.technologies = technologies;
    return this;
  }

  public Brief setFrameworks(Object frameworks) {
    this.frameworks = frameworks;
    return this;
  }

  public Brief setDeliverables(Object deliverables) {
    this.deliverables = deliverables;
    return this;
  }

  public Brief setAssessmentMethods(Object assessmentMethods) {
    this.assessmentMethods = assessmentMethods;
    return this;
  }

  public Brief setLearningMethods(Object learningMethods) {
    this.learningMethods = learningMethods;
    return this;
  }

  public Brief setPerformanceCriteria(String performanceCriteria) {
    this.performanceCriteria = performanceCriteria;
    return this;
  }

  public Brief setDeadline(Timestamp deadline) {
    this.deadline = deadline;
    return this;
  }

  public Brief setSkills(String skills) {
    this.skills = skills;
    return this;
  }

  public Brief setTeacher(User teacher) {
    this.teacher = teacher;
    return this;
  }

  public Brief setPromotion(Promotion promotion) {
    this.promotion = promotion;
    return this;
  }

//  Getters ===========================================

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

  public String getDescription() {
    return description;
  }

  public String getContext() {
    return context;
  }

  public Integer getLanguage() {
    return language;
  }

  public String getImage() {
    return image;
  }

  public Object getTechnologies() {
    return technologies;
  }

  public Object getFrameworks() {
    return frameworks;
  }

  public Object getDeliverables() {
    return deliverables;
  }

  public Object getAssessmentMethods() {
    return assessmentMethods;
  }

  public Object getLearningMethods() {
    return learningMethods;
  }

  public String getPerformanceCriteria() {
    return performanceCriteria;
  }

  public Timestamp getDeadline() {
    return deadline;
  }

  public String getSkills() {
    return skills;
  }

  public User getTeacher() {
    return teacher;
  }

  public Promotion getPromotion() {
    return promotion;
  }

  public List<Message> getMessages() {
    return messages;
  }

}
