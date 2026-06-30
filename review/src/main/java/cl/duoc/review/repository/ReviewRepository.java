package cl.duoc.review.repository;

import cl.duoc.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByDestinationId(UUID destinationId);
    List<Review> findByUserId(Long userId);
}