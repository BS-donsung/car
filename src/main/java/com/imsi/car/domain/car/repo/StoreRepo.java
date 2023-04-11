package com.imsi.car.domain.car.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Store;

public interface StoreRepo extends JpaRepository<Store, Integer> {

    @Query(value = "select s from Store s where s.spk like :spk")
    List<Store> findBySotreID(String spk);

}
