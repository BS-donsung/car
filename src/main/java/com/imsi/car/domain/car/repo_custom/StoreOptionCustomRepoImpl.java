package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.StoreOption;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class StoreOptionCustomRepoImpl implements StoreOptionCustomRepo {

    private final EntityManager em;

    public void saveStoreOptions(List<StoreOption> storeOptions) {
        // TODO Auto-generated method stub
        
    }

    
}
