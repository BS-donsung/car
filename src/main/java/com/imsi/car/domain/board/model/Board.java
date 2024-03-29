package com.imsi.car.domain.board.model;

import java.util.ArrayList;
import java.util.List;

import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.user.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString(exclude = { "replies" })
@Builder(toBuilder = true)
@Table(name = "board")
public class Board extends BaseTimeEntity {

    // 게시글의 pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    // 게시글의 작성자(유저의 pk를 fk로 사용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    // 게시글의 제목
    @Column(length = 100, nullable = false)
    private String title;

    // 게시글의 내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 게시글의 조회수
    @Column(length = 100, nullable = false)
    private int viewCount;

    // 게시글의 추천수
    @Column(length = 100, nullable = false)
    private int likes;

    // 게시글의 리플수
    @Builder.Default
    private int replyCount = 0;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    // 0: 일반 게시물
    // 1: 차량 게시물
    // 2: 칼럼 ... 
    private int type;

    private String thumbnail;

    @OneToOne
    @JoinColumn(name = "spk")
    private Store store;

}
