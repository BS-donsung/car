package com.imsi.car.domain.car.repo_custom;

import java.util.List;

import com.imsi.car.domain.car.dto.OptionDTO;
import com.imsi.car.domain.car.model.StoreOption;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class StoreOptionCustomRepoImpl implements StoreOptionCustomRepo {

    private final EntityManager em;

    public void saveUserOptions(List<StoreOption> storeOptions) {
        // String sql = "insert into useroption(car, option, isopt, user) values";
        
        
        // for (OptionDTO optionDTO: userOptionDTO.getOptions()) {
        //     int strKey = optionDTO.getOpk();
        //     String strValue = optionDTO.getOname();
        //     sql += "(:car, "+strKey+", "+strValue+", :user),";
        // }
        // sql = sql.substring(0, sql.length() - 1);
        // log.info(sql);
        // em.createNativeQuery(sql)
        // .setParameter("car", userOptionDTO.getCarDTO().getCid())
        // .setParameter("user", userOptionDTO.getUser())
        // .executeUpdate();

        // em.clear();
    }

    public void saveStoreOptions(List<StoreOption> storeOptions) {
        // TODO Auto-generated method stub
        
    }

    
}
