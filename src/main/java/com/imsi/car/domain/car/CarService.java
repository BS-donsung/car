package com.imsi.car.domain.car;

import java.util.List;

import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.dto.UserOptionDTO;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

public interface CarService {
    
    public List<Brand> listBrand();

    public List<Brand> listBrand(String keyword);

    public List<Segment> listSegment();

    public List<CarDTO> listCarByBrand(CarDTO carDTO);

    public List<CarDTO> listCarBySegment(CarDTO carDTO);

    public List<CarDTO> listCarByBrandAndSegment(CarDTO carDTO);

    public void storeUserOption(UserOptionDTO optionDTO);

    public CarDTO carInfo(String cid);

    public UserOptionDTO optionInfo(String cid);

}
