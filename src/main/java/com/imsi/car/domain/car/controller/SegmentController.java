package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.service.SegmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/segment")
@Log4j2
public class SegmentController {
    
    private final SegmentService segmentService;

    
    @GetMapping("/all")
    public List<Segment> listBrand(){
        // http://localhost:9000/segment/all
        log.info("/all");
        return segmentService.listSegment();
    }
}
