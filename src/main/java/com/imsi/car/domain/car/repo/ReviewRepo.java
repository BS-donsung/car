package com.imsi.car.domain.car.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Review;
import com.imsi.car.domain.car.repo_custom.ReviewCustomRepo;

public interface ReviewRepo extends JpaRepository<Review, Integer>, ReviewCustomRepo{
    

}
