package com.example.demo.service.impl;

import com.example.demo.entity.Company;
import com.example.demo.entity.Job;
import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }
    @Override
    public Review addReview(Long companyId, Review review) {
        Company company = companyService.getCompany(companyId).getBody();
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
        } else {
            throw new RuntimeException("Company not found");
        }
        return review;
    }
    @Override
    public Review updateReview(Long reviewId, Review review) {
        Optional<Review> existingReview = reviewRepository.findById(reviewId);
        if (existingReview.isPresent()) {
            Review updatedReview = existingReview.get();
            updatedReview.setTitle(review.getTitle());
            updatedReview.setDescription(review.getDescription());
            updatedReview.setRating(review.getRating());
            return reviewRepository.save(updatedReview);
        } else {
            throw new RuntimeException("Review not found");
        }
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new RuntimeException("Review not found");
        }
    }
}
