package dev.tejasgarde.jpa.controller;

import dev.tejasgarde.jpa.model.ReviewsByUser;
import dev.tejasgarde.jpa.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-reviews")
public class UserReviewsController {

  @Autowired
  private ReviewService reviewsService;


  @GetMapping("/{id}")
  public List<ReviewsByUser> getAllReviewsByUser(@PathVariable("id") Long userId){
    return reviewsService.getAllReviewsByUser(userId);
  }

}
