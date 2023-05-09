package com.imsi.car.domain.car.dto;


import java.util.List;

import com.imsi.car.domain.car.CarUtils;
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
    private int length;
    private int width;
    private int height;
    private int wheelBase;
    private int frontThread;
    private int rearTrhead;
    private String frontSuspesion;
    private String rearSuspension;

    private List<OptionDto> options;
    private List<ReviewDto> reviews;

    public CarDto(Car car){
        CarUtils carUtils = new CarUtils();
        this.cid = car.getCid();
        this.name = car.getName();
        this.brand = car.getBrand().getBrand();
        this.capacity = car.getCapacity();
        this.fuel_efficiency = car.getFuel_efficiency();
        this.engine = car.getEngine().getEngine();
        this.segment = car.getSegment().getSegment();
        this.imgurl = car.getImgurl();
        this.cost = car.getCost();
        this.options = carUtils.carOptionListToDtos(car.getCarOptions());
        this.reviews = carUtils.reviewListToReviewDtos(car.getReviews());
        this.length = car.getLength();
        this.width = car.getWidth();
        this.height = car.getHeight();
        this.wheelBase = car.getWheelBase();
        this.frontThread = car.getFrontThread();
        this.rearTrhead = car.getRearThread();
        this.frontSuspesion = car.getFrontSuspension();
        this.rearSuspension = car.getRearSuspension();
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