package com.imsi.car.domain.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.car.model.StoreOption;
import com.imsi.car.domain.car.repo_custom.StoreOptionCustomRepo;

public interface StoreOptionRepo extends JpaRepository<StoreOption,Integer>, StoreOptionCustomRepo {
    
}
