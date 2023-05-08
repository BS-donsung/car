package com.imsi.car.domain.car.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.user.model.User;

public interface StoreRepo extends JpaRepository<Store, Integer> {

    @Query(value = "select s from Store s where s.spk like :spk")
    Store findByStoreId(String spk);

    @Query(value = "select s from Store s where s.user like :user")
    List<Store> findByUsername(User user);

    @Query(value = "select s from Store s", nativeQuery = false)
    List<Store> findAllPage(Pageable pageable);
}
