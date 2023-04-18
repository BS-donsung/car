package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;

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
public class ReplyDto {
    private Long rno;
    private String text;
    private User user;
    private Board board;
    private Review review;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private BoardDto boardDto;
    private UserDto userDto;
    private ReviewDto reviewDto;

    public ReplyDto(Reply reply) {
        this.rno = reply.getRno();
        this.text = reply.getText();
        this.user = reply.getUser();
        this.board = reply.getBoard();
        this.review = reply.getReview();
        this.createdDate = reply.getCreatedDate();
        this.modifyDate = reply.getModifyDate();
        this.userDto = new UserDto();
    }

    public Reply toEntity() {
        Reply reply = Reply.builder()
                .rno(rno)
                .text(text)
                // .user(user)
                // .board(board)
                // .review(review)
                .user(userDto.toEntity())
                .board(boardDto.toEntity())
                .review(reviewDto.toEntity())
                .build();
        return reply;
    }
}
