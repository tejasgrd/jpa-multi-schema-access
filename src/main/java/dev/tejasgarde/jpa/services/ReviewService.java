package dev.tejasgarde.jpa.services;

import dev.tejasgarde.jpa.model.ReviewsByUser;

import java.util.List;

public interface ReviewService {
  List<ReviewsByUser> getAllReviewsByUser(Long userId);
}
