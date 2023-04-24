package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.Review;

import jakarta.transaction.Transactional;

public interface ReviewCustomRepo {
    @Transactional
    List<Review> findAllByCid(int cid);

}
