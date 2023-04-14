package com.imsi.car.domain.car.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.repo_custom.CarCustomRepo;

public interface CarRepo extends JpaRepository<Car, Integer>, CarCustomRepo {

    public Car findById(int id);

    public Car findByName(String name);

    @Query("SELECT c FROM Car c JOIN FETCH c.carOptions")
    public List<Car> findAllWithCarOptions();
}
