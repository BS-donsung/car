package com.imsi.car.domain.car.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
// @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    private String brand;
}
