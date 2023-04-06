package com.imsi.car.domain.user.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
public class User {
    @Id // primary key
    @Column(nullable=false)
	private String username;
    @Column(nullable=false)
	private String password;
	private String email;
	private String role; //ROLE_USER, ROLE_ADMIN
	private String nickname;
	private int exp;
	@CreationTimestamp
	private Timestamp createDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid; // 중요한 정보가 담겨있지 않지만, unique한 sid

	private String provider;
	private String providerId;

	@Builder
	public User(String username, String password, String email, String role, Timestamp createDate,String provider, String providerId, int exp, String nickname){
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.createDate = createDate;
		this.provider = provider;
		this.providerId = providerId;
		this.exp = exp;
		this.nickname = nickname;
	}
}
