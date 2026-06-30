package cl.duoc.review.service;

import cl.duoc.review.dto.ApiResponse;
import cl.duoc.review.dto.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

    private final WebClient.Builder webClientBuilder;

    public AuthService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public ApiResponse<UserDTO> validateToken(String token) {
        try {
            return webClientBuilder.build()
                .get()
                .uri("http://login/api/v1/auth/validate?token=" + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserDTO>>() {})
                .block();
        } catch (Exception e) {
            return null;
        }
    }
}