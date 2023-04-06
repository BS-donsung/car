package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.UserOption;
import com.imsi.car.domain.car.repo_custom.UserOptionCustomRepo;

public interface UserOptionRepo extends JpaRepository<UserOption,Integer>, UserOptionCustomRepo {
    
}
