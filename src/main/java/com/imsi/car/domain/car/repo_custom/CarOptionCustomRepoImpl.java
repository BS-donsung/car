package com.imsi.car.domain.car.repo_custom;

import java.util.List;


import com.imsi.car.domain.car.model.CarOption;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CarOptionCustomRepoImpl implements CarOptionCustomRepo {

    private final EntityManager em;


    public List<CarOption> findByCar(int car) {
        String sql = "select co from CarOption co where co.car.cid = :car";
        List<CarOption> result = em.createQuery(sql, CarOption.class)
        .setParameter("car",car)
        .getResultList();
        em.clear();
        return result;
    }
    
}
