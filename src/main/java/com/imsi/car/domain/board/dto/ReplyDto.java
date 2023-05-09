package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    // pk모음
    private int rno;
    private int bno;
    // private Long rvno;
    private String username;
    private String nickname;

    private String text;
    private String createdDate;
    private String modifyDate;

    private ReplyDto parent;

    public ReplyDto(Reply reply) {
        this.rno = reply.getRno();
        this.text = reply.getText();
        this.bno = reply.getBoard().getBno();
        // this.parent = reply.getParent();
        // this.rvno = reply.getReview().getRvno();
        
        this.nickname = reply.getUser().getNickname();
        this.username = reply.getUser().getUsername();
        this.createdDate = reply.getCreatedDate() != null ? reply.getFormattedCreatedDate()
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifyDate = reply.getModifyDate() != null ? reply.getFormattedModifyDate()
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Reply toEntity() {
        Reply reply = Reply.builder()
                .rno(rno)
                .board(Board.builder().bno(bno).build())
                .text(text)
                // .parent(parent)
                .user(User.builder().username(username).nickname(nickname).build())
                .build();

        return reply;
    }
}