package com.bezkoder.spring.jpa.onetoone.model;

import com.bezkoder.spring.jpa.onetoone.status.StatusPublished;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
  @Column(name = "id")
  private String id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private StatusPublished published;

  @OneToOne(mappedBy = "tutorial")
  private TutorialDetails tutorialDetails;

  public Tutorial() {

  }

  public Tutorial(String title, String description, StatusPublished published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public StatusPublished isPublished() {
    return published;
  }

  public void setPublished(StatusPublished isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }

}
