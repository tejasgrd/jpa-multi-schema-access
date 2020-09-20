package dev.tejasgarde.jpa.services;

import dev.tejasgarde.jpa.domain.reviews.Reviews;
import dev.tejasgarde.jpa.model.ReviewsByUser;
import dev.tejasgarde.jpa.respository.products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewService {

  @Autowired
  private ProductsRepository productsRepository;


  public List<ReviewsByUser> getAllReviewsByUser(Long userId){
    List<ReviewsByUser> result = Collections.emptyList();
    try {
      result =  productsRepository.findReviewsByUser(userId);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }
}
