package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.repo_custom.CarCustomRepo;

public interface CarRepo extends JpaRepository<Car,Integer>, CarCustomRepo{
    
    public Car findById(int id);
}
