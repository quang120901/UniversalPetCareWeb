package com.springboot.universalpetcare.service.review;

import org.springframework.data.domain.Page;

import com.springboot.universalpetcare.model.Review;

public interface IReviewService {
    void saveReview(Review review, Long reviewerId, Long veterinarianId);
    double getAverageRatingForVet(Long veterinarianId);
    void updateReview(Long reviewerId, Review review);
    Page<Review> findAllReviewsByUserId(Long userId, int page, int size);
}
