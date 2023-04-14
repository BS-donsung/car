package com.imsi.car.domain.car.dto;

import java.util.List;
import java.util.Map;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDTO {
    private CarDTO carDTO;
    private String user; // 이건 user 테이블의 name, user의 아이디를 통해 어떤 유저가 이걸 저장했는지를 알려줌
    private List<OptionDTO> options; 
}
