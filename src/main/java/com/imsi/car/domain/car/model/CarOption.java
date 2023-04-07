package com.imsi.car.domain.car.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CarOption {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copk;
@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car")
    private Car car;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option")
    private Option option; // 옵션 이름
    private boolean isopt; // 옵션 적용 여부
    private int cost;
}
