package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Segment;

public interface SegmentRepo extends JpaRepository<Segment, String>{
    
}
