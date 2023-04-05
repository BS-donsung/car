package com.imsi.car.domain.car;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.repo.BrandRepo;
import com.imsi.car.domain.car.repo.CarOptionRepo;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.CarStoreRepo;
import com.imsi.car.domain.car.repo.OptionRepo;
import com.imsi.car.domain.car.repo.SegmentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final BrandRepo brandRepo;
    private final SegmentRepo segmentRepo;
    private final CarOptionRepo carOptionRepo;
    private final CarRepo carRepo;
    private final CarStoreRepo carStoreRepo;
    private final OptionRepo optionRepo;

    public Car carInfo(CarDTO carDTO) {
        Car car = carRepo.findById(carDTO.getCid());

        return car;
    }

    public List<Brand> listBrand() {
        List<Brand> list = brandRepo.findAll();
        return list;
        
    }
    
    public List<Segment> listSegment() {
        List<Segment> list = segmentRepo.findAll();
        return list;
        
    }
    public List<Car> listCarByBrand(CarDTO carDTO) {
        List<Car> list = carRepo.findAllByBrand(brand);
        return list;
    }

    public List<Car> listCarBySegment(CarDTO carDTO) {
        List<Car> list = carRepo.findAllBySegment(segment);
        return list;
    }

    
}
