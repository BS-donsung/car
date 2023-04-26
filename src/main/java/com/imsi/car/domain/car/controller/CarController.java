package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.service.CarService;
import com.imsi.car.domain.car.service.StoreService;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@Log4j2
public class CarController {
    
    private final CarService carService;
    
    @GetMapping("/search")
    public List<CarDto> searchCars(@RequestParam @Nullable String segment, @RequestParam @Nullable String brand){
        // http://localhost:9000/car/search?segment=소형차
        // http://localhost:9000/car/search?brand=현대
        // http://localhost:9000/car/search?segment=소형차&brand=현대
        log.info("/search : {} {}",segment, brand);
        List<CarDto> list = null;
        CarDto carDto = CarDto.builder()
        .segment(segment)
        .brand(brand)
        .build();
        if(carDto.getBrand() != null && carDto.getSegment() != null){ // 둘 다 값을 줬다
            list = carService.listCarByBrandAndSegment(carDto);
        }else if(carDto.getBrand() != null){ // brand는 줬다
            list = carService.listCarByBrand(carDto);
        }else if(carDto.getSegment() != null){ // segment는 줬다
            list = carService.listCarBySegment(carDto);
        }else{ // 다 가져와봐라
            list = carService.listCar();
        }
        for (CarDto dto : list) {
            log.info("car : {}",dto);
        }
        return list;
    }

    
    @GetMapping("/search/car")
    public CarDto searchCar(@RequestParam String cid){
        // http://localhost:9000/car/search/car?cid=1
        log.info("/search/car : {} ",cid);
        CarDto carDto = carService.carInfo(cid);

        return carDto;
    }
    

}