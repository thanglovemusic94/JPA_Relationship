package com.bezkoder.spring.jpa.onetoone.controller;

import java.util.ArrayList;
import java.util.List;

import com.bezkoder.spring.jpa.onetoone.status.StatusPublished;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.onetoone.exception.ResourceNotFoundException;
import com.bezkoder.spring.jpa.onetoone.model.Tutorial;
import com.bezkoder.spring.jpa.onetoone.repository.TutorialDetailsRepository;
import com.bezkoder.spring.jpa.onetoone.repository.TutorialRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

  @Autowired
  TutorialRepository tutorialRepository;
  
  @Autowired
  private TutorialDetailsRepository detailsRepository;

  @GetMapping("")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title, @RequestParam(required = false) StatusPublished published) {
    List<Tutorial> tutorials = new ArrayList<Tutorial>();

    if (title == null && published == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTitleContaining(title, published).forEach(tutorials::add);

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tutorial> getTutorialById(String id) {
    Tutorial tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), StatusPublished.PUBLISHED));
    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    _tutorial.setTitle(tutorial.getTitle());
    _tutorial.setDescription(tutorial.getDescription());
    _tutorial.setPublished(tutorial.getPublished());
    
    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
    if (detailsRepository.existsById(id)) {
      detailsRepository.deleteById(id);
    }
    
    tutorialRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    tutorialRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    List<Tutorial> tutorials = tutorialRepository.findByPublished(StatusPublished.PUBLISHED);

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }
}
