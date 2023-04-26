package com.imsi.car.domain.car.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.service.CarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/option")
@RequiredArgsConstructor
@Log4j2
public class OptionController {
    private final CarService carService;

    @GetMapping("/search")
    public StoreDto searchOption(){
        // TODO : 얘는 뭐 하는 애임???
        return null;
    }
}
