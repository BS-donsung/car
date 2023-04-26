package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.model.Brand;

public interface BrandService {
    
    public List<Brand> listBrand();

    public List<Brand> listBrand(String keyword);
}
