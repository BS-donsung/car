package com.imsi.car.domain.user.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imsi.car.domain.board.model.Board;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties({ "boards" }) // boards 속성은 JSON으로 변환하지 않음(무한참조 방지 코드)
@Getter
@Setter
@ToString(exclude = { "boards" })

public class User {
	@Id // primary key
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	private String email;
	private String role; // ROLE_USER, ROLE_ADMIN
	private String nickname;
	private int exp;
	@CreationTimestamp
	private Timestamp createdDate;

	private String provider;
	private String providerId;

	@OneToMany(mappedBy = "writer", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Board> boards = new ArrayList<>();

	@Builder
	public User(String username, String password, String email, String role, Timestamp createdDate, String provider,
			String providerId, int exp, String nickname) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.createdDate = createdDate;
		this.provider = provider;
		this.providerId = providerId;
		this.exp = exp;
		this.nickname = nickname;
	}
}
