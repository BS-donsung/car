package com.imsi.car.domain.board.dto;

import java.util.List;

import com.imsi.car.domain.board.model.Board;
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
public class BoardDto {

    private int bno;
    private String title;
    private String content;
    private int viewCount;
    private int likes;
    private int replyCount;
    private String username;
    private String createdDate;
    private String modifyDate;
    private List<ReplyDto> replyDtos;

    public BoardDto(Board board) {
        this.bno = board.getBno();
        this.username = board.getUser().getUsername();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.likes = board.getLikes();
        this.replyCount = board.getReplyCount();
        this.createdDate = board.getFormattedCreatedDate();
        this.modifyDate = board.getFormattedModifyDate();
        // this.replyDtos = board.getReplies();

    }

    public Board toEntity() {
        Board board = Board.builder()
                .bno(bno)
                .user(User.builder().username(username).build())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .replyCount(replyCount)
                .build();
        return board;
    }
}