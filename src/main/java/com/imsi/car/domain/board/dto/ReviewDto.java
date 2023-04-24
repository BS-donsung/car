package com.imsi.car.domain.board.dto;

import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.car.dto.CarDTO;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long rvno;
    private Car car;
    private String title;
    private String content;
    private int viewCount;
    private int likes;
    private int replyCount;
    private UserDto writerDto;
    private String createdDate;
    private String modifyDate;
    // private List<Reply> replies;

    public ReviewDto(Review review) {
        this.rvno = review.getRvno();
        this.car = review.getCar();
        this.writerDto = new UserDto(review.getWriter());
        this.title = review.getTitle();
        this.content = review.getContent();
        this.viewCount = review.getViewCount();
        this.likes = review.getLikes();
        this.replyCount = review.getReplyCount();
        this.createdDate = review.getFormattedCreatedDate();
        this.modifyDate = review.getFormattedModifyDate();
    }

    public Review toEntity() {
        Review review = Review.builder()
                .rvno(rvno)
                .car(car)
                .writer(writerDto.toEntity())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .replyCount(replyCount)
                .build();
        log.info("ReviewToEntity로그 :" + writerDto.toEntity());
        return review;
    }
}