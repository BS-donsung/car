package com.imsi.car.domain.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class TestController {

    @GetMapping("/all/hihi")
    public String all(HttpServletRequest req) {
        log.info("setAttr : {}",req.getAttribute("token"));
        return "hihi";
    }

    @GetMapping("/user/hihi")
    public String user(HttpServletRequest req) {
        log.info("setAttr : {}",req.getAttribute("token"));
        return "hiih";
    }

    @GetMapping("/home")
    public void home(HttpServletResponse response) {
        // log.info("/home {}");
    	// String redirect_uri="http://localhost:8080/";
        // try {
        //     response.sendRedirect(redirect_uri);
        // } catch (Exception e) {
        // }
    }
    
}