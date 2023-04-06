package com.imsi.car.domain.car.repo_custom;

import java.util.Map;

import com.imsi.car.domain.car.dto.StoreDTO;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class UserOptionCustomRepoImpl implements UserOptionCustomRepo {

    private final EntityManager em;

    public void saveUserOptions(StoreDTO storeDTO) {
        String sql = "insert into useroption(car, option, isopt, user) values";
        
        
        for (Map.Entry<Integer, Integer> entry : storeDTO.getOptions().entrySet()) {
            int strKey = entry.getKey();
            int strValue = entry.getValue();
            sql += "(:car, "+strKey+", "+strValue+", :user),";
        }
        sql = sql.substring(0, sql.length() - 1);
        log.info(sql);
        em.createNativeQuery(sql)
        .setParameter("car", storeDTO.getCid())
        .setParameter("user", storeDTO.getUser())
        .executeUpdate();

        em.clear();
    }
}
