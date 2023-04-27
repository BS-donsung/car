package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.dto.ReviewDto;

import jakarta.transaction.Transactional;

public interface ReviewService {
    
public List<ReviewDto> reviewDtosByCid(int cid);

    public String saveReview(ReviewDto reviewDto, String username);

    public String deleteReview(ReviewDto reviewDto, String username);
}
