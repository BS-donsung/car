package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{
    
}
