package com.imsi.car.domain.mail;

import com.imsi.car.domain.mail.model.AdsDTO;

public interface MailService {
    

    // 회원가입 인증용 이메일
    public String joinEmail(String email);
    
    // 비밀번호 초기화용 이메일?
    public String passwordEmail(String email);

    // 전체 광고 메일
    public String adsEmail(AdsDTO adsDTO);

    
}
