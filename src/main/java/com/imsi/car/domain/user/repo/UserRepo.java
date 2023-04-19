package com.imsi.car.domain.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.user.model.User;

public interface UserRepo extends JpaRepository<User,Long>{
    // public User findById(int id);
    public User findByUsername(String username);


    Optional<User> findByProviderAndProviderId(String provider, String providerId);
    
    // email == 1인 사람만 긁어오기
    List<String> findEmailByAllowEmail(boolean allowEmail);

    public List<User> findByNicknameContaining(String keyword); //닉네임으로 검색
    // public Optional<User> findByEmailContaining(String email); // email로 검색
    public Optional<User> findByProviderAndProviderId(String provider, String providerId);

}
