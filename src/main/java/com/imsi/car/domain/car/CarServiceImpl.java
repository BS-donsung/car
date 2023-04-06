package com.imsi.car.domain.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.StoreDTO;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Engine;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.repo.BrandRepo;
import com.imsi.car.domain.car.repo.CarOptionRepo;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.OptionRepo;
import com.imsi.car.domain.car.repo.SegmentRepo;
import com.imsi.car.domain.car.repo.UserOptionRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService{

    private final BrandRepo brandRepo;
    private final SegmentRepo segmentRepo;
    private final CarOptionRepo carOptionRepo;
    private final CarRepo carRepo;
    private final OptionRepo optionRepo;
    private final UserOptionRepo userOptionRepo;

    public Car carInfo(CarDTO carDTO) {
        Car car = carRepo.findByName(carDTO.getName());
        return car;
    }

    public List<Brand> listBrand() {
        log.info("here");
        List<Brand> list = brandRepo.findAll();
        return list;
    }
    public List<Brand> listBrand(String keyword){
        List<Brand> list = brandRepo.findByBrand(keyword);
        return list;
    }
    
    public List<Segment> listSegment() {
        List<Segment> list = segmentRepo.findAll();
        return list;
    }
    public List<CarDTO> listCarByBrand(CarDTO carDTO) {
        List<Car> list = carRepo.findAllByBrand(new Brand(carDTO.getBrand()));
        
        
        return entityListToDtoList(list);
    }

    public List<CarDTO> listCarBySegment(CarDTO carDTO) {
        List<Car> list = carRepo.findAllBySegment(new Segment(0,carDTO.getSegment()));
        
        return entityListToDtoList(list);
    }

    public List<CarDTO> listCarByBrandAndSegment(CarDTO carDTO) {
        List<Car> list = carRepo.findAllByBrandAndSegment(new Brand(carDTO.getBrand()),new Segment(0,carDTO.getSegment()));
        
        return entityListToDtoList(list);
    }

    public void storeUserOption(StoreDTO storeDTO){
        userOptionRepo.saveUserOptions(storeDTO);
    }


    // util
    public List<CarDTO> entityListToDtoList(List<Car> list){
        List<CarDTO> result = new ArrayList<CarDTO>();
        for (Car car : list) {
            result.add(entityToDto(car));
        }
        return result;
    }

    public CarDTO entityToDto(Car car){
        CarDTO carDTO = CarDTO.builder()
        .cid(car.getCid())
        .name(car.getName())
        .brand(car.getBrand().getBrand())
        .capacity(car.getCapacity())
        .fuel_efficiency(car.getFuel_efficiency())
        .engine(car.getEngine().getEngine())
        .segment(segmentRepo.findSegmentById(car.getSegment().getSid()))
        .imgurl(car.getImgurl())
        .build();
        return carDTO;
    }
    public Car dtoToEntity(CarDTO carDTO){
        Car car = Car.builder()
        .cid(carDTO.getCid())
        .name(carDTO.getName())
        .brand(new Brand(carDTO.getBrand()))
        .capacity(carDTO.getCapacity())
        .fuel_efficiency(carDTO.getFuel_efficiency())
        .engine(new Engine(carDTO.getEngine()))
        .segment(new Segment(0, carDTO.getSegment()))

        .imgurl(carDTO.getImgurl())
        .build();
        return car;
    }

}
