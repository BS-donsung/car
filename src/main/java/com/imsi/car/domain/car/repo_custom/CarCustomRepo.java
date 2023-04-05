package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

public interface CarCustomRepo {
    List<Car> findAllByBrand(Brand brand);
    List<Car> findAllBySegment(Segment segment);
    List<Car> findAllByBrandAndSegment(Brand brand, Segment segment);
}
