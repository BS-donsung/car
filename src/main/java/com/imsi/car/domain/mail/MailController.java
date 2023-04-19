package com.imsi.car.domain.mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.mail.model.AdsDto;
import com.imsi.car.domain.mail.model.EmailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Log4j2
public class MailController  {
    
    private final MailService mailService;
    // err 발생시 msg는 "mailerr" 라는 문자열을 반환합니다
    // 이에 맞게 mailerr 문자열을 받았을시 vue에서의 처리가 필요합니다
    
    @PostMapping("/join")
    public String joinEmail(@RequestBody EmailDto emailDto){
        log.info("joining email : {}",emailDto);
        String msg = mailService.joinEmail(emailDto.getEmail());
        return msg;
    }
    @PostMapping("/passreset")
    public String passReset(@RequestBody EmailDto emailDto){
        log.info("reseting email : {}",emailDto);
        String msg = mailService.passwordEmail(emailDto.getEmail());
        return msg;
    }

    @PostMapping("/ads")
    public String adsEmail(@RequestBody AdsDto adsDTO){
        log.info("joining email : {}",adsDTO);
        String msg = mailService.adsEmail(adsDTO);
        return msg;
    }
    

}
