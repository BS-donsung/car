package com.imsi.car.domain.car.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imsi.car.domain.car.CarUtils;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private int spk;
    private CarDto carDto;
    private String user; // 이건 user 테이블의 name, user의 아이디를 통해 어떤 유저가 이걸 저장했는지를 알려줌
    private List<OptionDto> options; 


    public StoreDto (Store store){
        CarUtils carUtils = new CarUtils();
        this.spk = store.getSpk();
        // .options(storeOptionListToDtos(store.getStoreOptions())) 
        this.carDto = new CarDto(store.getCar());
        this.user = store.getUser().getUsername();
        this.options = carUtils.storeOptionListToDtos(store.getStoreOptions());
    }

    public Store toEntity(){
        Store store = Store.builder()
        .spk(this.spk)
        .car(this.carDto.toEntity())
        .user(User.builder().username(this.user).build())
        .build();
        return store;
    }
}
