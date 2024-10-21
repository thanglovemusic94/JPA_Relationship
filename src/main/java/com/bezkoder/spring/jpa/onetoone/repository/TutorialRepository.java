package com.bezkoder.spring.jpa.onetoone.repository;

import java.util.List;

import com.bezkoder.spring.jpa.onetoone.status.StatusPublished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jpa.onetoone.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, String> {
  List<Tutorial> findByPublished(StatusPublished published);

  @Query("select t from Tutorial t where (:title is null or upper(t.title) like upper(concat('%', :title, '%'))) " +
          "and (:published is null or t.published =:published)" )
  List<Tutorial> findByTitleContaining(@Param("title") String title, @Param("published") StatusPublished published);
}
