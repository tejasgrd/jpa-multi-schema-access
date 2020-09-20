package dev.tejasgarde.jpa.respository.reviews;

import dev.tejasgarde.jpa.domain.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long>  {
}
