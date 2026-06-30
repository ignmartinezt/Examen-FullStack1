package cl.duoc.review.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "El ID del destino es obligatorio")
    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull(message = "La nota es obligatoria")
    @Min(value = 1, message = "La nota mínima es 1")
    @Max(value = 5, message = "La nota máxima es 5")
    @Column(nullable = false)
    private Integer rating;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    @Column(length = 500, nullable = false)
    private String comment;
}