package com.imsi.car.domain.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    // TODO : 디폴트로 해당 옵션이 무조건 존재하는지 안 하는지!
    // A B C 옵션에 따른 해당 옵션 선택 여부

    private int opk;
    private String oname;
    private String oimg;
    private String oexpl;
    private boolean chk;

}
