package com.bezkoder.spring.jpa.onetoone.model;

import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tutorial_details")
public class TutorialDetails {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
  @Column(name = "id")
  private String id;

  @Column
  private Date createdOn;

  @Column
  private String createdBy;

//  @OneToOne(fetch = FetchType.LAZY)
//  @MapsId
//  @JoinColumn(name = "tutorial_id")
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "tutorial_id", referencedColumnName = "id")
  private Tutorial tutorial;

  public TutorialDetails() {
  }

  public TutorialDetails(String createdBy) {
    this.createdOn = new Date();
    this.createdBy = createdBy;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Tutorial getTutorial() {
    return tutorial;
  }

  public void setTutorial(Tutorial tutorial) {
    this.tutorial = tutorial;
  }

}
