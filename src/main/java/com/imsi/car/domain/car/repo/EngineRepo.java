package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.Engine;

public interface EngineRepo extends JpaRepository<Engine, String>{
    
}
