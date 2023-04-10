package com.imsi.car.domain.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.OptionDTO;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.StoreOption;

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
                .capacity(carDTO.getCapacity())
                .fuel_efficiency(carDTO.getFuel_efficiency())
                // .engine(new Engine(carDTO.getEngine()))
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
        .ischk(carOption.isOpt())
        .build();
        return optionDTO;
    }

    public OptionDTO entityToDto(StoreOption storeOption){
        OptionDTO optionDTO = OptionDTO.builder()
        .opk(storeOption.getSopk())
        .oname(storeOption.getOption().getOname())
        .oimg(storeOption.getOption().getOimg())
        .oexpl(storeOption.getOption().getOexpl())
        .ischk(storeOption.isOpt())
        .build();
        return optionDTO;
    }
}
