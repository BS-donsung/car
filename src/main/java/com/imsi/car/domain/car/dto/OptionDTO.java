package com.imsi.car.domain.car.dto;

import com.imsi.car.domain.car.model.CarOption;
import com.imsi.car.domain.car.model.Option;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.car.model.StoreOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    // TODO : 디폴트로 해당 옵션이 무조건 존재하는지 안 하는지!
    // A B C 옵션에 따른 해당 옵션 선택 여부

    private int opk;
    private String oname;
    private String oimg;
    private String oexpl;
    private boolean chk;


    public StoreOption toEntity(int spk) {
        StoreOption storeOption = StoreOption.builder()
        .opt(this.chk)
        .option(Option.builder().opk(this.opk).build())
        .store(Store.builder().spk(spk).build())
        .build();
        return storeOption;
    }

    public OptionDto(StoreOption storeOption){
        this.opk = storeOption.getSopk();
        this.oname = storeOption.getOption().getOname();
        this.oimg = storeOption.getOption().getOimg();
        this.oexpl = storeOption.getOption().getOexpl();
        this.chk = storeOption.isOpt();
    }
    
    public OptionDto(CarOption carOption){
        this.opk = carOption.getCopk();
        this.oname = carOption.getOption().getOname();
        this.oimg = carOption.getOption().getOimg();
        this.oexpl = carOption.getOption().getOexpl();
        this.chk = carOption.isOpt();
    }

    
}
