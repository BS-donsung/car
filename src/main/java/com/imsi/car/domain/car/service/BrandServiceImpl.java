package com.imsi.car.domain.car.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.repo.BrandRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BrandServiceImpl implements BrandService{

    private final BrandRepo brandRepo;
    
    public List<Brand> listBrand() {
        log.info("here");
        List<Brand> list = brandRepo.findAll();
        return list;
    }
    public List<Brand> listBrand(String keyword) {
        List<Brand> list = brandRepo.findByBrand(keyword);
        return list;
    }
}
