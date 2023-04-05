package com.imsi.car.domain.car;

import java.util.List;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

public interface CarService {
    
    public List<Brand> listBrand();

    public List<Segment> listSegment();

    public List<Car> listCarByBrand(Brand brand);

    public List<Car> listCarBySegment(Segment segment);

    public Car carInfo(Car car);
}
