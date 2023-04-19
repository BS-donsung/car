package com.imsi.car.domain.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.dto.OptionDto;
import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.model.StoreOption;

@Component
public class CarUtils {
    // util
    public List<CarDto> CarListToDtos(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : cars) {
            carDtos.add(new CarDto(car));
        }

        return carDtos;
    }

    public List<OptionDto> carOptionListToDtos(List<CarOption> carOptions){
        List<OptionDto> optionDtos = new ArrayList<>();
        for (CarOption carOption : carOptions) {
            optionDtos.add(new OptionDto(carOption));
        }

        return optionDtos;
    }

    public List<OptionDto> storeOptionListToDtos(List<StoreOption> storeOptions){
        List<OptionDto> optionDtos = new ArrayList<>();
        for(StoreOption storeOption : storeOptions){
            optionDtos.add(new OptionDto(storeOption));
        }
        return optionDtos;
    }

    public List<StoreOption> dtoListToStoreOptions(List<OptionDto> optionDtos,int spk){
        List<StoreOption> storeOptions = new ArrayList<>();
        for (OptionDto optionDto : optionDtos){
            storeOptions.add(optionDto.toEntity(spk));
        }
        return storeOptions;
    }

    public List<StoreDto> storeListToStoreDtos(List<Store> stores){
        List<StoreDto> storeDtos = new ArrayList<>();
        for(Store store : stores){
            storeDtos.add(new StoreDto(store));
        }
        return storeDtos;
    }


}