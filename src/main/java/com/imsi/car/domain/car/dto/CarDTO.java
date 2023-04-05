package com.imsi.car.domain.car.dto;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Engine;
import com.imsi.car.domain.car.model.Segment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDTO {
    private String name;
    private String brand;
    private int capacity;
    private int fuel_efficiency;
    private String engine;
    private String segment;
    private String imgurl;

    public CarDTO entityToDto(Car car){
        CarDTO carDTO = CarDTO.builder()
        .name(car.getName())
        .brand(car.getBrand().getBrand())
        .capacity(car.getCapacity())
        .fuel_efficiency(car.getFuel_efficiency())
        .engine(car.getEngine().getEngine())
        .segment(car.getSegemnt().getSegment())
        .imgurl(car.getImgurl())
        .build();
        return carDTO;
    }
    public Car dtoToEntity(CarDTO carDTO){
        Car car = Car.builder()
        .name(carDTO.getName())
        .brand(new Brand(carDTO.getBrand()))
        .capacity(carDTO.getCapacity())
        .fuel_efficiency(fuel_efficiency)
        .engine(new Engine(carDTO.getEngine()))
        .segemnt(new Segment(carDTO.getSegment()))
        .imgurl(carDTO.getImgurl())
        .build();
        return car;
    }
}
