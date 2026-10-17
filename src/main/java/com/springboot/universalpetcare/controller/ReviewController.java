package com.springboot.universalpetcare.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.data.domain.Page;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FOUND;

import com.springboot.universalpetcare.dto.ReviewDto;
import com.springboot.universalpetcare.exception.AlreadyExistsException;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Review;
import com.springboot.universalpetcare.request.ReviewUpdateRequest;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.review.IReviewService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;
import com.springboot.universalpetcare.ultis.UrlMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RequiredArgsConstructor
@RequestMapping(UrlMapping.REVIEWS)
@RestController
public class ReviewController {
    private final ModelMapper modelMapper;
    private final IReviewService reviewService;

    @PostMapping(UrlMapping.SUBMIT_REVIEW)
    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review review,
                                                @RequestParam Long reviewerId,
                                                @RequestParam Long vetId) {
        try {
            Review savedReview = reviewService.saveReview(review, reviewerId, vetId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, savedReview.getId()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_REVIEW)
    public ResponseEntity<ApiResponse> updateReview(@RequestBody ReviewUpdateRequest updateRequest, 
                                                    @PathVariable Long reviewId) {
        try {
            Review updateReview = reviewService.updateReview(reviewId, updateRequest);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, updateReview));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_REVIEW)
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
      
    }

    @GetMapping(UrlMapping.GET_USER_REVIEWS)
    public ResponseEntity<ApiResponse> getReviewsByUserID(@PathVariable Long userId, 
                                                        @RequestParam(defaultValue = "0") int page, 
                                                        @RequestParam(defaultValue = "5") int size) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, page, size);
        Page<ReviewDto> reviewDtos = reviewPage.map((element) -> modelMapper.map(element, ReviewDto.class));
        return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, reviewDtos));
    }

    @GetMapping("/vet/{vetId}/get-average-rating")
    public ResponseEntity<ApiResponse> getAverageRatingForVet(@PathVariable Long vetId) {
        double averageRating = reviewService.getAverageRatingForVet(vetId);
        return ResponseEntity.ok(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, averageRating));
    }
}
