package com.imsi.car.domain.car.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class CarOption {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copk;
    @OneToMany
    private Car car;
    private Option option; // 옵션 이름
    private boolean isopt; // 옵션 적용 여부
}
