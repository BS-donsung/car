package com.imsi.car.domain.mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.mail.model.AdsDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MailController  {
    
    private final MailService mailService;
    // err 발생시 msg는 "mailerr" 라는 문자열을 반환합니다
    // 이에 맞게 mailerr 문자열을 받았을시 vue에서의 처리가 필요합니다
    
    @PostMapping("/join")
    public String joinEmail(@RequestBody String email){
        log.info("joining email : {}",email);
        String msg = mailService.joinEmail(email);
        return msg;
    }
    @PostMapping("/passreset")
    public String passReset(@RequestBody String email){
        log.info("reseting email : {}",email);
        String msg = mailService.passwordEmail(email);
        return msg;
    }

    @PostMapping("/ads")
    public String adsEmail(@RequestBody AdsDTO adsDTO){
        log.info("joining email : {}",adsDTO);
        String msg = mailService.adsEmail(adsDTO);
        return msg;
    }
    

}
