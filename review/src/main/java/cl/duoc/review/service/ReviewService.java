package cl.duoc.review.service;

import cl.duoc.review.model.Review;
import cl.duoc.review.repository.ReviewRepository;
import cl.duoc.review.dto.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final WebClient.Builder webClientBuilder;

    public ReviewService(ReviewRepository reviewRepository, WebClient.Builder webClientBuilder) {
        this.reviewRepository = reviewRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Review createReview(Review review, String token) {
        Boolean destinationExists = webClientBuilder.build()
            .get()
            .uri("http://destination/api/v1/destinations/" + review.getDestinationId())
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(ApiResponse.class)
            .map(response -> response.getCode() == 200)
            .onErrorReturn(false)
            .block();

        if (Boolean.FALSE.equals(destinationExists)) {
            throw new RuntimeException("El destino especificado no existe o no se pudo validar");
        }

        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByDestination(UUID destinationId) {
        return reviewRepository.findByDestinationId(destinationId);
    }
}