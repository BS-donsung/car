package com.imsi.car.domain.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    
    private final UserService userService;

    @PostMapping("/join")
    public String doJoin(@RequestBody User user){
        // 회원가입 메소드
        log.info("user : {}",user);
        String remsg = "로그인 실패";
        if(userService.join(user)) remsg = "로그인 성공";
        return remsg;
    }
    @PostMapping("/chkuser")
    public boolean doChkUser(@RequestBody User user){
        // 아이디 중복 확인
        log.info("user : {}",user);
        boolean flag = true;
        if(userService.isDupUser(user)) flag = false;
        return flag;
    }
    @GetMapping("/getuser")
    public UserDto getUser(HttpServletRequest req) {
        String username = (String)req.getAttribute("username");
        log.info("/getUser : {}",username);
        return userService.findUserByUsername(username);
    }
}
