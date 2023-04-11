package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Option;

public interface OptionRepo extends JpaRepository<Option, Integer>{
    
}
