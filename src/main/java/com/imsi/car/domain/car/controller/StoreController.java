package com.imsi.car.domain.car.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.car.dto.StoreDto;
import com.imsi.car.domain.car.service.CarService;
import com.imsi.car.domain.car.service.StoreService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/store")
@Log4j2
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/allstores")
    public List<StoreDto> findAllStores(@RequestParam(defaultValue = "1") Integer page){
        log.info("/allstores : {}",page);
        List<StoreDto> storeDtos = storeService.listStorePage(page);
        return storeDtos;
    }

    @GetMapping("/mystores")
    public List<StoreDto> findMyStores(HttpServletRequest req){
        String username = (String)req.getAttribute("username");
        log.info("/mystores : {}", username);
        List<StoreDto> stroeDtos = storeService.listOptionByUsername(username);
        return stroeDtos;
    }
    @GetMapping("/mystore")
    public StoreDto findMyStore(HttpServletRequest req, @RequestParam String spk){
        String username = (String)req.getAttribute("username");
        log.info("/mystore : {} , {}",username, spk);
        StoreDto storeDto = storeService.optionInfo(spk);
        return storeDto;
    }
    @GetMapping("/view/{spk}")
    public StoreDto storeDto(@PathVariable String spk){
        log.info("/store/view/{} ",spk);
        return storeService.viewStore(spk);
    }
    

    @PostMapping("/store")
    public void storeCar(@RequestBody StoreDto storeDto, HttpServletRequest req){
        // http://localhost:9000/car/store
        /*
        {
            "carDto" : {
                "cid" : 1
            },
            "user" : "banana",
            "options" : [
                { "opk" : 1, "opt" : 1 }
            ]
        }
         */
        String username = (String) req.getAttribute("username");
        storeDto.setUser(username);
        log.info("/stroe : {}",storeDto);
        storeService.storeUserOption(storeDto);
    }

    @DeleteMapping("/delete")
    public void deleteCar(@RequestParam int spk, HttpServletRequest req){
        StoreDto storeDto = new StoreDto(spk, null, null, null);
        String username = (String) req.getAttribute("username");
        storeDto.setUser(username);
        log.info("/delete : {}",storeDto);
        storeService.deleteStore(storeDto);
    }
}
