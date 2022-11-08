package com.simplon.brief5simplonclone.entities;

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

  @Column(name = "description")
  private String description;

  @Column(name = "context")
  private String context;

  @Column(name = "language", nullable = false)
  private Integer language;

  @Column(name = "image")
  private String image;

  @Column(name = "technologies")
  private Object technologies;

  @Column(name = "frameworks")
  private Object frameworks;

  @Column(name = "deliverables")
  private Object deliverables;

  @Column(name = "assessment_methods")
  private Object assessmentMethods;

  @Column(name = "learning_methods")
  private Object learningMethods;

  @Column(name = "performance_criteria")
  private String performanceCriteria;

  @Column(name = "deadline")
  private Timestamp deadline;

  @Column(name = "skills")
  private String skills;

  //  Relations ========================================
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "user_id", nullable = false)
  private User teacher;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "promotion_id", nullable = false)
  private Promotion promotion;

  @OneToMany(mappedBy = "brief", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private List<Message> messages;

//  Setters ===========================================


  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public void setLanguage(Integer language) {
    this.language = language;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setTechnologies(Object technologies) {
    this.technologies = technologies;
  }

  public void setFrameworks(Object frameworks) {
    this.frameworks = frameworks;
  }

  public void setDeliverables(Object deliverables) {
    this.deliverables = deliverables;
  }

  public void setAssessmentMethods(Object assessmentMethods) {
    this.assessmentMethods = assessmentMethods;
  }

  public void setLearningMethods(Object learningMethods) {
    this.learningMethods = learningMethods;
  }

  public void setPerformanceCriteria(String performanceCriteria) {
    this.performanceCriteria = performanceCriteria;
  }

  public void setDeadline(Timestamp deadline) {
    this.deadline = deadline;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
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
