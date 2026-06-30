package cl.duoc.review.controller;

import cl.duoc.review.dto.ApiResponse;
import cl.duoc.review.model.Review;
import cl.duoc.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createReview(
            @Valid @RequestBody Review review,
            @RequestHeader("Authorization") String authHeader) {
        
        String token = authHeader.replace("Bearer ", "");
        Review createdReview = reviewService.createReview(review, token);
        
        ApiResponse<Review> response = new ApiResponse<>(
            201, 
            "Reseña creada exitosamente", 
            createdReview
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Review>>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        ApiResponse<List<Review>> response = new ApiResponse<>(
            200, 
            "Listado de reseñas obtenido exitosamente", 
            reviews
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByDestination(
            @PathVariable UUID destinationId) {
        
        List<Review> reviews = reviewService.getReviewsByDestination(destinationId);
        ApiResponse<List<Review>> response = new ApiResponse<>(
            200, 
            "Reseñas del destino obtenidas exitosamente", 
            reviews
        );
        return ResponseEntity.ok(response);
    }
}