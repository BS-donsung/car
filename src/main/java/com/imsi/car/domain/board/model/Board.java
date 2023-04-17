package com.imsi.car.domain.board.model;

import java.time.LocalDateTime;
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
@Table(name = "board")
public class Board extends BaseTimeEntity {
    
    // 게시글의 pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 게시글의 작성자(유저의 pk를 fk로 사용)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer", updatable = false)
    // @JsonIgnoreProperties("boards") // 무한참조 방지코드
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

    private LocalDateTime modifyDate;
    
    //보드 - 리플 관계 //리포지토리의 find 어쩌고가 참조하는 것으로 인한 무한참조 이슈
    // @JsonManagedReference(value = "board-replies") // replies 필드를 직렬화할 때 참조하는 객체를 정의(무한참조 방지)
    //YARC에서 415에러 및 무한참조 발생, 사용하지 말것
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    // 뷰 카운트
    public void addViewCount() {
        this.viewCount++;
    }

    // 빌더
    public Board(Long id, String title, String content, User writer, int viewCount, int likes) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
        this.likes = 0;

    }

}
