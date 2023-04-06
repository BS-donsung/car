package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.CarService;
import com.imsi.car.domain.car.model.Brand;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    
    private final CarService carService;

    
    @GetMapping("/all")
    public List<Brand> listBrand(){
        // 모든 브랜드를 전송합니다
        return carService.listBrand();
    }
    @GetMapping("/")
    public List<Brand> searchBrand(String keyword){
        return carService.listBrand(keyword);
    }
}
