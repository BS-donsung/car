package com.imsi.car.domain.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.OptionDTO;
import com.imsi.car.domain.car.dto.StoreDTO;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Engine;
import com.imsi.car.domain.car.model.Option;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.model.StoreOption;
import com.imsi.car.domain.user.model.User;

@Component
public class CarUtils {
    // util
    public List<CarDTO> CarListToDTO(List<Car> list) {
        List<CarDTO> result = new ArrayList<>();
        for (Car car : list) {
            result.add(entityToDto(car));
        }

        return result;
    }
    public List<OptionDTO> carOptionListToDTO(List<CarOption> list){
        List<OptionDTO> result = new ArrayList<>();
        for (CarOption carOption : list) {
            result.add(entityToDto(carOption));
        }

        return result;
    }

    public List<StoreOption> dtoListToStoreOptions(List<OptionDTO> list,int spk){
        List<StoreOption> result = new ArrayList<>();
        for (OptionDTO optionDTO : list){
            result.add(dtoToEntity(optionDTO,spk));
        }
        return result;
    }

    public CarDTO entityToDto(Car car) {
        CarDTO carDTO = CarDTO.builder()
                .cid(car.getCid())
                .name(car.getName())
                .brand(car.getBrand().getBrand())
                .capacity(car.getCapacity())
                .fuel_efficiency(car.getFuel_efficiency())
                .engine(car.getEngine().getEngine())
                .segment(car.getSegment().getSegment())
                .imgurl(car.getImgurl())
                .cost(car.getCost())
                .options(carOptionListToDTO(car.getCarOptions()))
                .build();
        return carDTO;
    }

    public Car dtoToEntity(CarDTO carDTO) {
        Car car = Car.builder()
                .cid(carDTO.getCid())
                .name(carDTO.getName())
                // .brand(new Brand(carDTO.getBrand()))
                // .engine(new Engine(carDTO.getEngine()))
                // .capacity(carDTO.getCapacity())
                // .fuel_efficiency(carDTO.getFuel_efficiency())
                // .segment(new Segment(0, carDTO.getSegment()))
                .cost(carDTO.getCost())
                .imgurl(carDTO.getImgurl())
                .build();
        return car;
    }

    public OptionDTO entityToDto(CarOption carOption) {
         // CarOption -> OptionDTO로 변경
        OptionDTO optionDTO = OptionDTO.builder()
        .opk(carOption.getCopk())
        .oname(carOption. getOption().getOname())
        .oimg(carOption.getOption().getOimg())
        .oexpl(carOption.getOption().getOexpl())
        .chk(carOption.isOpt())
        .build();
        return optionDTO;
    }

    public OptionDTO entityToDto(StoreOption storeOption){
        OptionDTO optionDTO = OptionDTO.builder()
        .opk(storeOption.getSopk())
        .oname(storeOption.getOption().getOname())
        .oimg(storeOption.getOption().getOimg())
        .oexpl(storeOption.getOption().getOexpl())
        .chk(storeOption.isOpt())
        .build();
        return optionDTO;
    }

    public Store dtoToEntity(StoreDTO storeDTO){
        Store store = Store.builder()
        .car(dtoToEntity(storeDTO.getCarDTO()))
        .user(User.builder().username(storeDTO.getUser()).build())
        .build();
        return store;
    }
    
    public StoreOption dtoToEntity(OptionDTO optionDTO,int spk){
        StoreOption storeOption = StoreOption.builder()
        .opt(optionDTO.isChk())
        .option(Option.builder().opk(optionDTO.getOpk()).build())
        .store(Store.builder().spk(spk).build())
        .build();
        return storeOption;
    }
}