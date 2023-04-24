package com.imsi.car.domain.car.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.CarUtils;
import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.repo.CarOptionRepo;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.OptionRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;

    private final CarUtils carUtils;

    public CarDto carInfo(String cid) {
        Car car = carRepo.findById(Integer.parseInt(cid));
        CarDto carDto = new CarDto(car);
        return carDto;
    }

    public List<CarDto> listCar(){
        List<Car> list = carRepo.findAll();

        return carUtils.CarListToDtos(list);
    }

    public List<CarDto> listCarByBrand(CarDto carDto) {
        List<Car> list = carRepo.findAllByBrand(new Brand(carDto.getBrand()));
 
        return carUtils.CarListToDtos(list);
    }

    public List<CarDto> listCarBySegment(CarDto carDto) {
        List<Car> list = carRepo.findAllBySegment(new Segment(0, carDto.getSegment()));

        return carUtils.CarListToDtos(list);
    }

    public List<CarDto> listCarByBrandAndSegment(CarDto carDto) {
        List<Car> list = carRepo.findAllByBrandAndSegment(new Brand(carDto.getBrand()),
                new Segment(0, carDto.getSegment()));

        return carUtils.CarListToDtos(list);
    }

}
