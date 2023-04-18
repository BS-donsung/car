package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;

import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long rvno;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private int viewCount;
    private int likes;
    // private List<Reply> replies;
    private int replyCount;
    private UserDto writerDto;

    public ReviewDto(Review review) {
        this.rvno = review.getRvno();
        this.writerDto = new UserDto(review.getWriter());
        this.title = review.getTitle();
        this.content = review.getContent();
        this.viewCount = review.getViewCount();
        this.likes = review.getLikes();
        this.createdDate = review.getCreatedDate();
        this.modifyDate = review.getModifyDate();
        this.replyCount = review.getReplyCount();
    }

    public Review toEntity() {
        Review review = Review.builder()
                .rvno(rvno)
                .writer(writerDto.toEntity())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .replyCount(replyCount)
                .build();
        return review;
    }
}