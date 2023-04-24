package com.imsi.car.domain.car.dto;


import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.car.model.Review;
import com.imsi.car.domain.car.model.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private int rid;
    private String nickname;
    private int cid;
    private String content;

    public ReviewDto(Review review){
        this.rid = review.getRid();
        this.nickname = review.getUser().getNickname();
        this.cid = review.getCar().getCid();
        this.content = review.getContent();
    }
    
    public Review toEntity(){
        Review review = Review.builder()
        .rid(this.rid)
        .user(User.builder().nickname(this.nickname).build())
        .car(Car.builder().cid(this.cid).build())
        .content(this.content)
        .build();
        return review;
    }

}
