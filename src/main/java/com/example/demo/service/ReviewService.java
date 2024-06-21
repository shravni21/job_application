package com.example.demo.service;

import com.example.demo.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Review addReview(Long companyId, Review review);
    Review updateReview(Long reviewId, Review review);
    void deleteReview(Long reviewId);
}
