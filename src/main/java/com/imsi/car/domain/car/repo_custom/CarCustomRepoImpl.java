package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarCustomRepoImpl implements CarCustomRepo{
    private final EntityManager em;

    public List<Car> findAllByBrand(Brand brand) {
        final String SQL  = "SELECT c from CAR c " +
        "WHERE c.brand = :brand";
        List<Car> result = em.createNativeQuery(SQL, Car.class)
        .setParameter("brand", brand.getBrand())
        .getResultList();
        em.clear();
        return result;
    }

    public List<Car> findAllBySegment(Segment segment) {
        final String SQL  = "SELECT c.* from car c "+
        "WHERE c.segment = (SELECT sid FROM segment WHERE segment LIKE :segment)";
        List<Car> result = em.createNativeQuery(SQL, Car.class)
        .setParameter("segment", segment.getSegment())
        .getResultList();
        em.clear();
        return result;
    }

    public List<Car> findAllByBrandAndSegment(Brand brand, Segment segment){
        final String SQL  = "SELECT c from CAR c " +
        "inner join segment s" + 
        "on s.segment = :segment" +
        "WHERE c.brand = :brand";
        List<Car> result = em.createNativeQuery(SQL, Car.class)
        .setParameter("brand", brand.getBrand())
        .setParameter("segment", segment.getSegment())
        .getResultList();
        em.clear();
        return result;
    }
    
}
