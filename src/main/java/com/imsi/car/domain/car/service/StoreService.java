package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.dto.StoreDto;

public interface StoreService {
    
    public void storeUserOption(StoreDto storeDto);

    public StoreDto optionInfo(String cid);
    
    public List<StoreDto> listOptionByUsername(String username);
}
