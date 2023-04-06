package com.imsi.car.domain.car.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, String>{
    
    @Query(value = "select br from Brand br where br.brand like %:brand%")
    public List<Brand> findByBrand(String brand);
}
