package com.imsi.car.domain.car.service;

import java.util.List;

import com.imsi.car.domain.car.dto.StoreDto;

public interface StoreService {
    
    public void storeUserOption(StoreDto storeDto);

    public StoreDto optionInfo(String cid);

    public StoreDto viewStore(String spk);
    
    public List<StoreDto> listOptionByUsername(String username);

    public List<StoreDto> listStorePage(int page);

    public void deleteStore(StoreDto storeDto);
}
