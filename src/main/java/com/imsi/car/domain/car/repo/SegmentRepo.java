package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Segment;

import jakarta.transaction.Transactional;

public interface SegmentRepo extends JpaRepository<Segment, Integer>{

    @Transactional
    @Query(value = "select sg from Segment sg where sg.segment like :segment")
    public Segment findBySegment(String segment);

    @Query(value = "select sg.segment from Segment sg where sg.sid like :sid")
    public String findSegmentById(Integer sid);
}
