package com.imsi.car.domain.car.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.CarUtils;
import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.model.StoreOption;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.StoreOptionRepo;
import com.imsi.car.domain.car.repo.StoreRepo;
import com.imsi.car.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final StoreOptionRepo storeOptionRepo;
    private final CarRepo carRepo;

    private final CarUtils carUtils;

    public List<StoreDto> listOptionByUsername(String username) {
        List<Store> stores = storeRepo.findByUsername(User.builder().username(username).build());
        List<StoreDto> result = carUtils.storeListToStoreDtos(stores);
        return result;
    }

    public StoreDto optionInfo(String spk) {
        Store store = storeRepo.findByStoreId(spk);
        StoreDto storeDto = new StoreDto(store);
        return storeDto;
    }

    public StoreDto viewStore(String spk){
        Store store = storeRepo.findByStoreId(spk);
        StoreDto storeDto = new StoreDto(store);
        return storeDto;
    }


    public void storeUserOption(StoreDto storeDto) {
        Store store = storeDto.toEntity();
        store = storeRepo.save(store);
        List<StoreOption> options = carUtils.dtoListToStoreOptions(storeDto.getOptions(), store.getSpk());
        storeOptionRepo.saveAll(options);

    }

    public List<StoreDto> listStorePage(int page){
        Pageable pageable = PageRequest.of(page - 1,100, Sort.by("spk").descending());
        List<Store> result = storeRepo.findAllPage(pageable);
        return carUtils.storeListToStoreDtos(result);
    }

    public void deleteStore(StoreDto storeDto){
        storeRepo.deleteById(storeDto.getSpk());
    }

}
