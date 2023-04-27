package com.imsi.car.domain.board.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imsi.car.domain.user.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Entity
@ToString(exclude = { "board",  "user" })
public class Reply extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;

  private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore // 무한참조 방지코드
  @JoinColumn(name = "parent")
  private Reply parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
  @JsonIgnore // 무한참조 방지코드
  private List<Reply> child = new ArrayList<>();

  // 유저, 보드를 직렬화에서 제외한 것을 취소함
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user")
  // @JsonBackReference(value = "user-replies")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "board")
  @JsonBackReference // 무한 참조 방지용
  private Board board;

}