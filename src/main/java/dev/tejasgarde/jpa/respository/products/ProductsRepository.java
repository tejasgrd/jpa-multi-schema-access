package dev.tejasgarde.jpa.respository.products;

import dev.tejasgarde.jpa.domain.products.Products;
import dev.tejasgarde.jpa.model.ReviewsByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
  @Transactional(timeout = 300)
  @Query(nativeQuery = true)
  List<ReviewsByUser> findReviewsByUser(Long userId)throws SQLException;;
}
