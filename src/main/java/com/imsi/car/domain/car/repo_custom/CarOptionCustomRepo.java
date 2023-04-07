package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.CarOption;

public interface CarOptionCustomRepo {
    List<CarOption> findByCar(int car);
    
}
