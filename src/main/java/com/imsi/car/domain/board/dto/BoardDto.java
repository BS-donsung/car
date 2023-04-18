package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;

import com.imsi.car.domain.board.model.Board;
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
public class BoardDto {

    private Long bno;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private int viewCount;
    private int likes;
    // private List<Reply> replies;
    private int replyCount;
    private UserDto writerDto;

    public BoardDto(Board board) {
        this.bno = board.getBno();
        this.writerDto = new UserDto(board.getWriter());
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.likes = board.getLikes();
        this.replyCount = board.getReplyCount();
        this.createdDate = board.getCreatedDate();
        this.modifyDate = board.getModifyDate();
    }

    public Board toEntity() {
        Board board = Board.builder()
                .bno(bno)
                .writer(writerDto.toEntity())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .replyCount(replyCount)
                .build();
        log.info("로그 :" + writerDto.toEntity());
        return board;
    }
}