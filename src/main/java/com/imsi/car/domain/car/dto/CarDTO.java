package com.imsi.car.domain.car.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDTO {
    private int cid;
    private String name;
    private String brand;
    private int capacity;
    private double fuel_efficiency;
    private String engine;
    private String segment;
    private String imgurl;
    private int cost;

    
}