package com.imsi.car.domain.car.repo_custom;

import com.imsi.car.domain.car.dto.StoreDTO;

import jakarta.transaction.Transactional;

public interface UserOptionCustomRepo {
    @Transactional
    void saveUserOptions(StoreDTO storeDTO);
}
