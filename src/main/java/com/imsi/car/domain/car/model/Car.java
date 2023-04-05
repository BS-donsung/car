package com.imsi.car.domain.car.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Car {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name; // 차량 이름
    @ManyToOne
    private Brand brand; // 차량 브랜드
    private int capacity; // 배기량
    private int fuel_efficiency; // 키로미터당 연비
    @ManyToOne
    private Engine engine; // 엔진 방식
    @ManyToOne
    private Segment segemnt;
    private String imgurl;
    
}
