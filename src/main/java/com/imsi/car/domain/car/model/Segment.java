package com.imsi.car.domain.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Segment {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid; // 차급은 별도의 순서가 필요하다고 판단, int형 pk를 통해 관리
    private String segment;
}
