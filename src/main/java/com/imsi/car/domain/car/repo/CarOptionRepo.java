package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.CarOption;

public interface CarOptionRepo extends JpaRepository<CarOption, Integer>{

}
