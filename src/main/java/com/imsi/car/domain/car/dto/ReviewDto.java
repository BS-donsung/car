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
    private String username;
    private int cid;

    public ReviewDto(Review review){
        this.rid = review.getRid();
        this.username = review.getUser().getUsername();
        this.cid = review.getCar().getCid();
    }
    
    public Review toEntity(){
        Review review = Review.builder()
        .user(User.builder().username(this.username).build())
        .car(Car.builder().cid(this.cid).build())
        .build();
        return review;
    }

}
