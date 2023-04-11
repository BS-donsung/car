package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.StoreOption;

import jakarta.transaction.Transactional;

public interface StoreOptionCustomRepo {
    @Transactional
    void saveStoreOptions(List<StoreOption> storeOptions);
}
