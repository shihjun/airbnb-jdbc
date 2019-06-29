package com.example.demo.controllers;

import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {

  @Autowired
  ReviewRepository reviewRepository;

  @GetMapping(value = "/allreviews", produces = "application/json")
  public List<Review> displayReviews() {
    return reviewRepository.getAllReviews();
  }

  @GetMapping(value = "/properties/{id}/reviews", produces = "application/json")
  public List<Review> displayReviewsByProperty(@PathVariable("id") int id) {
    return reviewRepository.getReviewsByProperty(id);
  }

  @GetMapping(value = "/reviews", produces = "application/json")
  public List<Review> displayReviewsByRating(@RequestParam int rating) {
    return reviewRepository.getReviewsByRating(rating);
  }

  @PostMapping(value = "/properties/{id}/reviews", produces = "application/json")
  public Review createReview(@RequestBody Review review, @PathVariable("id") int id) {
    reviewRepository.createReview(id, review);
    return review;
  }
}