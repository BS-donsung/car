package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, String>{
    
}
