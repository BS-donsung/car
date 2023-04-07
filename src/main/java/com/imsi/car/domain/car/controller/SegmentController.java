package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.CarService;
import com.imsi.car.domain.car.model.Segment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/segment")
@Log4j2
public class SegmentController {
    
    private final CarService carService;

    
    @GetMapping("/all")
    public List<Segment> listBrand(){
        log.info("/all");
        return carService.listSegment();
    }
}
