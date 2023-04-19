package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

import jakarta.transaction.Transactional;

public interface CarCustomRepo {
    @Transactional
    List<Car> findAllByBrand(Brand brand);
    @Transactional
    List<Car> findAllBySegment(Segment segment);
    @Transactional
    List<Car> findAllByBrandAndSegment(Brand brand, Segment segment);
}
