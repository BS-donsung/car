package com.imsi.car.domain.car.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.CarService;
import com.imsi.car.domain.car.dto.UserOptionDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/option")
@RequiredArgsConstructor
@Log4j2
public class OptionController {
    private final CarService carService;

    @GetMapping("/search/option")
    public UserOptionDTO searchOption(@RequestParam String cid){
        log.info("/search/option : {}",cid);
        UserOptionDTO optionDTO = null;
        optionDTO = carService.optionInfo(cid);
        return optionDTO;
    }
    
}
