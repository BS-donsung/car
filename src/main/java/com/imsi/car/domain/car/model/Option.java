package com.imsi.car.domain.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opk;
    private String oname; // 옵션 이름
    private String oimg; // 옵션 사진
    private String oexpl; // 옵션 설명
}
