package com.imsi.car.domain.car;

import java.util.List;

import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Segment;

public interface CarService {
    
    public List<Brand> listBrand();

    public List<Brand> listBrand(String keyword);

    public List<Segment> listSegment();

    public List<CarDto> listCar();

    public List<CarDto> listCarByBrand(CarDto carDto);

    public List<CarDto> listCarBySegment(CarDto carDto);

    public List<CarDto> listCarByBrandAndSegment(CarDto carDto);

    public void storeUserOption(StoreDto storeDto);

    public CarDto carInfo(String cid);

    public StoreDto optionInfo(String cid);

    public List<StoreDto> listOptionByUsername(String username);

    

}
