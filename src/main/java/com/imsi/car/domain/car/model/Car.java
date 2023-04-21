package com.imsi.car.domain.car.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name; // 차량 이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand")
    private Brand brand; // 차량 브랜드

    // 제원 정보
    private int capacity; // 배기량
    private double fuel_efficiency; // 키로미터당 연비
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine")
    private Engine engine; // 구동방식
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segment")
    private Segment segment; // 차급
    private int length; // 전장
    private int width; // 전폭
    private int height; // 전고
    private int wheelBase; // 축거
    private int frontThread; //윤거전
    private int rearThread; //윤거후
    private String frontSuspension; // 전륜 서스펜션
    private String rearSuspension; // 후륜 서스펜션



    private String imgurl;
    private int cost;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private List<CarOption> carOptions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    private List<Review> reviews;

}
