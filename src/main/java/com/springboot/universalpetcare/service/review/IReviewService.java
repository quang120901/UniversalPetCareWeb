package com.springboot.universalpetcare.service.review;

import org.springframework.data.domain.Page;

import com.springboot.universalpetcare.model.Review;
import com.springboot.universalpetcare.request.ReviewUpdateRequest;

public interface IReviewService {
    Review saveReview(Review review, Long reviewerId, Long veterinarianId);
    double getAverageRatingForVet(Long veterinarianId);
    Review updateReview(Long reviewerId, ReviewUpdateRequest review);
    Page<Review> findAllReviewsByUserId(Long userId, int page, int size);
    void deleteReview(Long reviewerId);
}
