package com.imsi.car.domain.car;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.car.dto.CarDto;
import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Option;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.model.StoreOption;
import com.imsi.car.domain.car.repo.BrandRepo;
import com.imsi.car.domain.car.repo.CarOptionRepo;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.OptionRepo;
import com.imsi.car.domain.car.repo.SegmentRepo;
import com.imsi.car.domain.car.repo.StoreOptionRepo;
import com.imsi.car.domain.car.repo.StoreRepo;
import com.imsi.car.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService {

    private final BrandRepo brandRepo;
    private final SegmentRepo segmentRepo;
    private final CarRepo carRepo;
    private final CarOptionRepo carOptionRepo;
    private final OptionRepo optionRepo;
    private final StoreRepo storeRepo ;
    private final StoreOptionRepo storeOptionRepo;

    private final CarUtils carUtils;

    public CarDto carInfo(String cid) {
        Car car = carRepo.findById(Integer.parseInt(cid));
        CarDto carDto = new CarDto(car);
        return carDto;
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

    public List<CarDto> listCarByBrand(CarDto carDto) {
        List<Car> list = carRepo.findAllByBrand(new Brand(carDto.getBrand()));

        for (Car car : list) {
            log.info("car : {} ",car);
        }
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

    public void storeUserOption(StoreDto storeDto) {
        Store store = storeDto.toEntity();
        store = storeRepo.save(store);
        List<StoreOption> options = carUtils.dtoListToStoreOptions(storeDto.getOptions(),store.getSpk());
        storeOptionRepo.saveAll(options);
        
    }

    public StoreDto optionInfo(String cid) {
        Car car = carRepo.findById(Integer.parseInt(cid));
        StoreDto optionDto = StoreDto.builder()
        .options(carUtils.carOptionListToDtos(car.getCarOptions()))
        .carDto(new CarDto(car))
        .build();
        
        return optionDto;
    }

    public List<StoreDto> listOptionByUsername(String username){
        List<Store> stores= storeRepo.findByUsername(User.builder().username(username).build());
        List<StoreDto> result = carUtils.storeListToStoreDtos(stores);
        return result;
    }

    



}
