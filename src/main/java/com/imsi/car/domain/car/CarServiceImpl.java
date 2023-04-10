package com.imsi.car.domain.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.StoreDTO;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Engine;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.repo.BrandRepo;
import com.imsi.car.domain.car.repo.CarOptionRepo;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.OptionRepo;
import com.imsi.car.domain.car.repo.SegmentRepo;
import com.imsi.car.domain.car.repo.StoreOptionRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService {

    private final BrandRepo brandRepo;
    private final SegmentRepo segmentRepo;
    private final CarOptionRepo carOptionRepo;
    private final CarRepo carRepo;
    private final OptionRepo optionRepo;
    private final StoreOptionRepo userOptionRepo;

    private final CarUtils carUtils;

    public CarDTO carInfo(String cid) {
        Car car = carRepo.findById(Integer.parseInt(cid));
        CarDTO carDTO = carUtils.entityToDto(car);
        return carDTO;
    }

    public List<Brand> listBrand() {
        log.info("here");
        List<Brand> list = brandRepo.findAll();
        for (Brand brand : list) {
            log.info("brand : {}", brand);
        }
        return list;
    }

    public List<Brand> listBrand(String keyword) {
        List<Brand> list = brandRepo.findByBrand(keyword);
        return list;
    }

    public List<Segment> listSegment() {
        List<Segment> list = segmentRepo.findAll();
        return list;
    }

    public List<CarDTO> listCarByBrand(CarDTO carDTO) {
        List<Car> list = carRepo.findAllByBrand(new Brand(carDTO.getBrand()));

        for (Car car : list) {
            log.info("car : {} ",car);
        }
        return carUtils.CarListToDTO(list);
    }

    public List<CarDTO> listCarBySegment(CarDTO carDTO) {
        List<Car> list = carRepo.findAllBySegment(new Segment(0, carDTO.getSegment()));

        return carUtils.CarListToDTO(list);
    }

    public List<CarDTO> listCarByBrandAndSegment(CarDTO carDTO) {
        List<Car> list = carRepo.findAllByBrandAndSegment(new Brand(carDTO.getBrand()),
                new Segment(0, carDTO.getSegment()));

        return carUtils.CarListToDTO(list);
    }

    public void storeUserOption(StoreDTO optionDTO) {
        userOptionRepo.saveUserOptions(optionDTO);
    }

    public StoreDTO optionInfo(String cid) {
        List<CarOption> list = carOptionRepo.findByCar(Integer.parseInt(cid));
        StoreDTO optionDTO = StoreDTO.builder()
        // .sid(list.get(0).getCar().getCid())
        .user("banana")
        .build();
        
        

        return optionDTO;
    }

    



}
