package com.imsi.car.domain.car.model;

import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class CarStore {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csid;
    @OneToMany
    private User user;
    @OneToMany
    private Car car;
    @OneToMany
    private CarOption carOption;
}
