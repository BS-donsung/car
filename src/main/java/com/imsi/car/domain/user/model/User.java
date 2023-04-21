package com.imsi.car.domain.user.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imsi.car.domain.board.model.Board;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"boards"}) // boards 속성은 JSON으로 변환하지 않음(무한참조 방지 코드)
@Getter
@Setter
@ToString(exclude = {"boards"})
@EntityListeners(AuditingEntityListener.class) // createdDate를 위해?
public class User {
    @Id // primary key
    @Column(nullable=false)
	private String username;
    @Column(nullable=false)
	private String password;
	private String email;
	private String role; //ROLE_USER, ROLE_ADMIN
	private String nickname;
	@ColumnDefault("0")
	private int exp;
	@CreationTimestamp
	private Timestamp createDate;


	// mail, email 수신 여부 설정
	@Column(columnDefinition="tinyint(1) default 1")
    private boolean allowEmail;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean allowSms;

	@CreatedDate
	private Date createdDate;

	private String provider;
	private String providerId;
	@Column(unique = true)
	private int sid;
	@OneToMany(mappedBy = "writer", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Board> boards = new ArrayList<>();
	


}
