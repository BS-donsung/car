package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.car.model.Brand;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.Segment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CarCustomRepoImpl implements CarCustomRepo{
    private final EntityManager em;

    @Transactional
    public List<Car> findAllByBrand(Brand brand) {
        log.info("brand : {}",brand.getBrand());
        final String SQL  = "SELECT c from Car c " +
        "WHERE c.brand = :brand";
        List<Car> result = em.createQuery(SQL, Car.class)
        .setParameter("brand", brand)
        .getResultList();
        // em.clear();
        return result;
    }

    @Transactional
    public List<Car> findAllBySegment(Segment segment) {
        final String SQL  = "SELECT c from Car c "+
        "WHERE c.segment = (SELECT sid FROM Segment WHERE segment LIKE :segment)";
        // "WHERE c.segment = :segment";
        List<Car> result = em.createQuery(SQL, Car.class)
        .setParameter("segment", segment.getSegment())
        .getResultList();
        // em.clear();
        return result;
    }



    @Transactional
    public List<Car> findAllByBrandAndSegment(Brand brand, Segment segment){
        log.info("brand : {}",brand.getBrand());
        final String SQL  = "SELECT c from Car c " +
        "WHERE c.brand = :brand " + 
        "AND c.segment = (SELECT sid FROM Segment WHERE segment LIKE :segment)";
        List<Car> result = em.createQuery(SQL, Car.class)
        .setParameter("brand", brand)
        .setParameter("segment", segment)
        .getResultList();
        // em.clear();
        return result;
    }
    
}
