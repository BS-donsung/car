package com.imsi.car.domain.mail;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;
import com.imsi.car.domain.mail.model.AdsDto;
import com.imsi.car.domain.user.repo.UserRepo;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailServiceImpl implements MailService{
    private final UserRepo userRepo;

    @Override
    public String adsEmail(AdsDto adsDTO) {
        String msg = "";
        List<String> emails = userRepo.findEmailByAllowEmail(true);
        String[] emailsArr = emails.toArray(new String[emails.size()]);

        try {
            sendEmail(adsDTO.getTitle(), adsDTO.getMsg(),emailsArr);
        } catch (Exception e) {
            log.error("mailerr",e);
            msg = "mailerr";
        }
        return msg;
    }

    @Override
    public String joinEmail(String email) {
        String title = "대충 회원가입 이메일확인 제목";
        String code = generateCode(); // code를 이용해서 content를 만들기
        String content = "대충 회원가입 이메일확인 내용";

        try {
            sendEmail(title, content, email);
        } catch (Exception e) {
            log.error("mailerr",e);
            code = "mailerr";
        }
        
        return code;
    }

    @Override
    public String passwordEmail(String email) {
        String title = "대충 임시비밀번호라는 제목";
        String code = generateCode(); // code를 이용해서 content를 만들기
        String content = "대충 임시비밀번호 쓰여있는 내용";

        try {
            sendEmail(title, content, email);
        } catch (Exception e) {
            log.error("mailerr",e);
            code = "mailerr";
        }

        return code;
    }

    public void sendEmail(String title, String content,String... tomails) throws Exception{
        final String KEY = "";

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kimsw3445@gmail.com", KEY);
			}
		});
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("kimsw3445@gmail.com","관리자","utf-8"));
        for (String tomail : tomails) {
            log.info("tomail : {}",tomail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
        }
        message.setSubject(title);
        message.setContent(content,"text/html; charset=utf-8");

        Transport.send(message);
    }

    public String generateCode(){
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        String result = uuid.toString().substring(0,8);
        return result;
    }
    
    
}
