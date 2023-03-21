package com.imsi.car.domain.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imsi.car.domain.user.model.User;

public interface UserRepo extends JpaRepository<User,Long>{
    public User findByUsername(String username);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
