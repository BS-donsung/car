package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String createdDate;
    private String modifyDate;
    private BoardDto boardDto;
    private UserDto userDto;
    private ReviewDto reviewDto;

    public ReplyDto(Reply reply) {
        this.rno = reply.getRno();
        this.text = reply.getText();
        this.user = reply.getUser();
        this.board = reply.getBoard();
        this.review = reply.getReview();
        this.createdDate = reply.getFormattedCreatedDate();
        this.createdDate = reply.getCreatedDate() != null ? reply.getFormattedCreatedDate()
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifyDate = reply.getModifyDate() != null ? reply.getFormattedModifyDate()
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.userDto = new UserDto(reply.getUser());
        this.boardDto = new BoardDto(reply.getBoard());
    }

    // 추가
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setBoardDto(BoardDto boardDto) {
        this.boardDto = boardDto;
    }

    public void setReviewDto(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }

    public Reply toEntity() {
        Reply reply = Reply.builder()
                .rno(rno)
                .text(text)
                .user(userDto != null ? userDto.toEntity() : null)
                .board(boardDto != null ? boardDto.toEntity() : (board != null ? board : null))
                .review(reviewDto != null ? reviewDto.toEntity() : (review != null ? review : null))
                .build();
        return reply;
    }

}
