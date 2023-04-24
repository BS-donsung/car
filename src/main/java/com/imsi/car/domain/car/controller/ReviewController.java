package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.dto.ReviewDto;
import com.imsi.car.domain.car.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/review")
@RestController
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping("/all")
    public List<ReviewDto> all(){
        log.info("/all : ");
        List<ReviewDto> result = reviewService.allReviewDtos();

        return result;
    }

    @PostMapping("/save")
    public void save(@RequestBody ReviewDto reviewDto, HttpServletRequest req){
        String username = (String)req.getAttribute("username");
        reviewService.saveReview(reviewDto, username);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ReviewDto reviewDto, HttpServletRequest req){
        String username = (String)req.getAttribute("username");
        reviewService.deleteReview(reviewDto, username);

    }
}
