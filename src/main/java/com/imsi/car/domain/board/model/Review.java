package com.imsi.car.domain.board.model;

import java.util.ArrayList;
import java.util.List;

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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
@ToString
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer", updatable = false)
    // @JsonIgnoreProperties("boards") // 무한참조 방지코드
    private User writer;
    private String title;
    private String content;
    private int viewCount;
    private int likes;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public Review(Long id, User writer, String title, String content, int viewCount, int likes){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
        this.likes = 0;
    }
}
