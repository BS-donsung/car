package com.imsi.car.domain.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Option {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opk;
    private String oname; // 옵션 이름
}
