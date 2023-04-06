package com.imsi.car.domain.car.dto;

import java.util.Map;

import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.car.model.UserOption;
import com.imsi.car.domain.user.model.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDTO {
    private int cid; // 이건 car 테이블의 pk, 어떤 car를 골랐는지를 알려줌
    private String user; // 이건 user 테이블의 name, user의 아이디를 통해 어떤 유저가 이걸 저장했는지를 알려줌
    // 여기부터는 UserOption임
    private Map<Integer, Integer> options; // key : Option.key , value : 0,1
}
