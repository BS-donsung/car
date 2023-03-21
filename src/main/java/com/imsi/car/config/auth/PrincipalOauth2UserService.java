package com.imsi.car.config.auth;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;
import com.imsi.car.config.auth.provider.FacebookUserInfo;
import com.imsi.car.config.auth.provider.GoogleUserInfo;
import com.imsi.car.config.auth.provider.KakaoUserInfo;
import com.imsi.car.config.auth.provider.NaverUserInfo;
import com.imsi.car.config.auth.provider.OAuth2UserInfo;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info(userRequest.getClientRegistration().getRegistrationId());
        log.info(super.loadUser(userRequest));
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("attibute : " + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "google":
                oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
                break;
            case "facebook":
                oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
                break;
            case "naver":
                oAuth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
                break;
            case "kakao":
                oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
                break;
            default:
                log.info("지원하지 않는 OAuth 입니다");
                break;
        }
        Optional<User> userOptional = userRepo.findByProviderAndProviderId(oAuth2UserInfo.getProvider(),
                oAuth2UserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.setEmail(oAuth2UserInfo.getEmail());
            userRepo.save(user);
        } else {
            UUID uuid = Generators.timeBasedEpochGenerator().generate();
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = User.builder()
                    .username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
                    .email(oAuth2UserInfo.getEmail())
                    .password(passwordEncoder.encode("놀자곰"))
                    .role("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .exp(0)
                    .nickname("user"+uuid.toString())
                    .build();
            userRepo.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }

}
