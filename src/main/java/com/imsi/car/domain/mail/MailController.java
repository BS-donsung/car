package com.imsi.car.domain.mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.mail.model.AdsDto;
import com.imsi.car.domain.mail.model.MailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@Log4j2
public class MailController  {
    
    private final MailService mailService;
    // err 발생시 msg는 "mailerr" 라는 문자열을 반환합니다
    // 이에 맞게 mailerr 문자열을 받았을시 vue에서의 처리가 필요합니다
    
    @PostMapping("/join")
    public String joinEmail(@RequestBody MailDto mailDto){
        log.info("joining email : {}",mailDto);
        String msg = mailService.joinEmail(mailDto.getEmail());
        return msg;
    }
    @PostMapping("/passreset")
    public String passReset(@RequestBody MailDto mailDto){
        log.info("reseting email : {}",mailDto);
        String msg = mailService.passwordEmail(mailDto.getEmail());
        return msg;
    }

    @PostMapping("/ads")
    public String adsEmail(@RequestBody AdsDto adsDTO){
        log.info("joining email : {}",adsDTO);
        String msg = mailService.adsEmail(adsDTO);
        return msg;
    }

    @PostMapping("/inquiry")
    public String inquiryEmail(@RequestBody MailDto mailDto){
        log.info("/inquiry : {}",mailDto );
        String msg = mailService.inquiryMail(mailDto);
        log.info("msg : {}",msg);
        return msg;
    }
    

}
