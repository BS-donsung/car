package com.imsi.car.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.extern.log4j.Log4j2;

// loginProcessUrl("/login") 
// login 요청시 자동으로 UserDetailsService 타입으로 IOC된 loadUserByUsername 함수가 실행
@Service
@Log4j2
public class PrincipalDetailsService implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        log.info(username);
        User user = userRepo.findByUsername(username);
        log.info(user);
        return new PrincipalDetails(user);
    }
}
