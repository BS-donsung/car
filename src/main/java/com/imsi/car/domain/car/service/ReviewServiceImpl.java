package com.imsi.car.domain.car.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.CarUtils;
import com.imsi.car.domain.car.dto.ReviewDto;
import com.imsi.car.domain.car.model.Review;
import com.imsi.car.domain.car.repo.ReviewRepo;
import com.imsi.car.domain.user.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepo reviewRepo;
    private final CarUtils carUtils;

    @Override
    public List<ReviewDto> reviewDtosByCid(int cid) {
        List<Review> reviews = reviewRepo.findAllByCid(cid);
        List<ReviewDto> reviewDtos = carUtils.reviewListToReviewDtos(reviews);

        return reviewDtos;
    }

    
    @Override
    @Transactional
    public String saveReview(ReviewDto reviewDto, String username) {
        Review review = reviewDto.toEntity();
        review.setUser(User.builder().username(username).build());
        reviewRepo.save(review);

        return null;
    }
    
    @Override
    @Transactional
    public String deleteReview(ReviewDto reviewDto, String username) {
        // TODO Auto-generated method stub
        return null;
    }
}
