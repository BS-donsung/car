package com.imsi.car.domain.car.model;

import java.util.List;

import com.imsi.car.domain.board.model.Review;

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
import lombok.NoArgsConstructor;

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
    private int capacity; // 배기량
    private double fuel_efficiency; // 키로미터당 연비
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine")
    private Engine engine; // 엔진 방식
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segment")
    private Segment segment;
    private String imgurl;
    private int cost;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private List<CarOption> carOptions;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Review> reviews;

}
