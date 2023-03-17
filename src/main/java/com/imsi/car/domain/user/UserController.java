package com.imsi.car.domain.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/join")
    public String doJoin(User user){
        // 회원가입 메소드
        String remsg = "로그인 실패";
        if(userService.join(user)) remsg = "로그인 성공";
        return remsg;
    }
    @PostMapping("/chkuser")
    public boolean doChkUser(User user){
        // 아이디 중복 확인
        boolean flag = true;
        if(userService.isDupUser(user)) flag = false;
        return flag;
    }
}
