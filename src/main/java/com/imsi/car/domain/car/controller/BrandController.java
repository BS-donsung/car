package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.service.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
@Log4j2
public class BrandController {
    
    private final BrandService brandService;

    
    @GetMapping("/all")
    public List<Brand> listBrand(){
        log.info("/all");
        return brandService.listBrand();
    }
    @GetMapping("/")
    public List<Brand> searchBrand(String keyword){
        log.info("/ : {}",keyword);
        return brandService.listBrand(keyword);
    }
}
