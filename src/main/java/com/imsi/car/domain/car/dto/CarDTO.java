package com.imsi.car.domain.car.dto;


import java.util.List;

import com.imsi.car.domain.car.model.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private int cid;
    private String name;
    private String brand;
    private int capacity;
    private double fuel_efficiency;
    private String engine;
    private String segment;
    private String imgurl;
    private int cost;

    private List<OptionDto> options;

    public CarDto(Car car){
        this.cid = car.getCid();
        this.name = car.getName();
        this.brand = car.getBrand().getBrand();
        this.capacity = car.getCapacity();
        this.fuel_efficiency = car.getFuel_efficiency();
        this.engine = car.getEngine().getEngine();
        this.segment = car.getSegment().getSegment();
        this.imgurl = car.getImgurl();
        this.cost = car.getCost();
        this.options = null;
    }

    public Car toEntity(){
        Car car = Car.builder()
                .cid(this.cid)
                .name(this.name)
                // .brand(new Brand(this.brand))
                // .engine(new Engine(this.engine))
                // .capacity(this.capacity)
                // .fuel_efficiency(this.fuel_efficiency)
                // .segment(new Segment(0, this.segment))
                .cost(this.cost)
                .imgurl(this.imgurl)
                .build();
        return car;
    }
}