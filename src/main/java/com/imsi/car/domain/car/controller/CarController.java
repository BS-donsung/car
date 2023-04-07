package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.CarService;
import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.UserOptionDTO;

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
    public List<CarDTO> searchCars(@RequestParam @Nullable String segment, @RequestParam @Nullable String brand){
        log.info("/search : {} {}",segment, brand);
        List<CarDTO> list = null;
        CarDTO carDTO = CarDTO.builder()
        .segment(segment)
        .brand(brand)
        .build();
        if(carDTO.getBrand() != null && carDTO.getSegment() != null){ // 둘 다 값을 줬다
            list = carService.listCarByBrandAndSegment(carDTO);
        }else if(carDTO.getBrand() != null){ // brand는 줬다
            list = carService.listCarByBrand(carDTO);
        }else if(carDTO.getSegment() != null){ // segment는 줬다
            list = carService.listCarBySegment(carDTO);
        }
        for (CarDTO dto : list) {
            log.info("car : {}",dto);
        }
        return list;
    }

    @PostMapping("/store")
    public List<CarDTO> storeCar(@RequestBody UserOptionDTO optionDTO){
        log.info("/stroe : {}",optionDTO);
        List<CarDTO> list = null;
        carService.storeUserOption(optionDTO);


        return list;
    }
    @GetMapping("/search/car")
    public CarDTO searchCar(@RequestParam String cid){
        log.info("/search/car : {} ",cid);
        CarDTO carDTO = null;
        carDTO = carService.carInfo(cid);

        return carDTO;
    }
    

}