package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.dto.CarDto;

public interface CarService {
    
    public List<CarDto> listCar();

    public List<CarDto> listCarByBrand(CarDto carDto);

    public List<CarDto> listCarBySegment(CarDto carDto);

    public List<CarDto> listCarByBrandAndSegment(CarDto carDto);

    public CarDto carInfo(String cid);

}
