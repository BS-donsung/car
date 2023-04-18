package com.imsi.car;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Segment;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.repo.CarRepo;
import com.imsi.car.domain.car.repo.StoreRepo;

@SpringBootTest
class CarApplicationTests {

	@Autowired
	private StoreRepo storeRepo;
	@Autowired
	private CarRepo carRepo;

	@Test
	@Transactional
	void contextLoads() {
		List<Store> list = storeRepo.findAll();
		for (Store store : list) {
			System.out.println(store);
			
		}
	}

	@Test
	@Transactional
	void contextLoads2() {
		Store store = storeRepo.findBySotreID("1");
		System.out.println(store);
	}

	@Test
	@Transactional
	void contextLoads3() {
		List<Car> list = carRepo.findAllWithCarOptions();
		for (Car car : list) {
			List<CarOption> cos = car.getCarOptions();
			for (CarOption co : cos) {
				System.out.println(co);
			}
		}
	}


}
