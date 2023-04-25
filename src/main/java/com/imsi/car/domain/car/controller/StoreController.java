package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.CarService;
import com.imsi.car.domain.car.dto.StoreDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/store")
@Log4j2
@RequiredArgsConstructor
public class StoreController {
    private final CarService carService;

    @GetMapping("/mystores")
    public List<StoreDto> findMyStores(HttpServletRequest req){
        log.info("/mystores : {}", req.getAttribute("usernmae"));
        
        return carService.listOptionByUsername("gi");
    }
    @GetMapping("/mystore")
    public StoreDto findMyStore(HttpServletRequest req, @RequestParam String cid){
        log.info("/mystore : {} , {}",req.getAttribute("username"), cid);
        StoreDto storeDto = carService.optionInfo(cid);
        return storeDto;
    }
}
