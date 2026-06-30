CREATE TABLE reviews (
    id VARCHAR(36) PRIMARY KEY,
    rating INT NOT NULL,
    comment VARCHAR(500) NOT NULL,
    destination_id VARCHAR(36) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_rating CHECK (rating >= 1 AND rating <= 5)
);