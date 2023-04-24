package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.model.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class ReviewCustomRepoImpl implements ReviewCustomRepo {
    private final EntityManager em;

    @Transactional
    public List<Review> findAllByCid(int cid){
        final String SQL = "SELECT rv from Review rv "+
        "WHERE rv.car.cid = :cid";
        List<Review> reviews = em.createQuery(SQL,Review.class)
        .setParameter("cid", cid)
        .getResultList();
        return reviews; 
    }
    

}
