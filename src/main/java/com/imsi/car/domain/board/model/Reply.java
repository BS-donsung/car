package com.imsi.car.domain.board.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = { "board", "review", "user" })
public class Reply extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;

  private String username;

  private String text;

  // 유저, 보드를 직렬화에서 제외한 것을 취소함
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user")
  // @JsonBackReference(value = "user-replies")
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "board")
  @JsonBackReference // 무한 참조 방지용
  private Board board;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  // @JsonBackReference//무한 참조 방지용
  @JoinColumn(name = "review")
  private Review review;

  public static List<String> getUsernames(List<Reply> replies) {
    return replies.stream().map(Reply::getUsername).collect(Collectors.toList());
  }

}