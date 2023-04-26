package com.imsi.car.domain.car.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.repo.SegmentRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SegmentServiceImpl implements SegmentService{

    private final SegmentRepo segmentRepo;

    @Override
    public List<Segment> listSegment() {
        List<Segment> list = segmentRepo.findAll();
        return list;
    }
    
}
