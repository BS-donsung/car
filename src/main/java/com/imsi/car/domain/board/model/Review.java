package com.imsi.car.domain.board.model;

import java.util.ArrayList;
import java.util.List;

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
@ToString(exclude = { "writer", "replies" })
@Builder(toBuilder = true)
@Table(name = "review")
public class Review extends BaseTimeEntity {

    // 게시글의 pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rvno;

    // 게시글의 작성자(유저의 pk를 fk로 사용)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer", updatable = false)
    // @JsonIgnoreProperties("reviews") // 무한참조 방지코드
    private User writer;

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

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    // 뷰 카운트
    public void addViewCount() {
        this.viewCount++;
    }

    // 리플라이 카운트
    public void addReplyCount() {
        this.replyCount++;
    }

    public void subtractReplyCount() {
        this.replyCount--;
    }

    // 빌더
    public Review(Long rvno, String title, String content, User writer, int viewCount, int likes, int replyCount) {
        this.rvno = rvno;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likes = likes;
        this.replyCount = replyCount;
    }

}
