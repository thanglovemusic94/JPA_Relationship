package com.bezkoder.spring.jpa.onetoone.repository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jpa.onetoone.model.TutorialDetails;

@Repository
public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails, String> {
  @Transactional
  void deleteById(String id);
  
  @Transactional
  void deleteByTutorialId(String tutorialId);
}
