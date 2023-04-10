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
    // TODO : 이걸 UserOptionDTO에 넣기
    // Option Entity를 UserOptionDTO에서 OptionDTO에 담기
    // 그렇게 해서 OptionController에서는 UserOptionDTO를 Front로 보내주자

    private int opk;
    private String oname;
    private String oimg;
    private String oexpl;
    private boolean ischk;

}
