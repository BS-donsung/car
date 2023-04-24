package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.dto.ReviewDto;

public interface ReviewService {
    
    public List<ReviewDto> allReviewDtos();

    public String saveReview(ReviewDto reviewDto, String username);

    public String deleteReview(ReviewDto reviewDto, String username);
}
