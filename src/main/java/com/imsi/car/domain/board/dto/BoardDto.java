package com.imsi.car.domain.board.dto;

import java.util.List;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
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
    private int viewCount;
    private int likes;
    private int replyCount;
    private UserDto writerDto;
    private String createdDate;
    private String modifyDate;
    private List<Reply> replies;
    private List<BoardDto> boardDtos;
    private List<ReplyDto> replyDtos;

    public BoardDto(Board board) {
        this.bno = board.getBno();
        this.writerDto = new UserDto(board.getWriter());
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.likes = board.getLikes();
        this.replyCount = board.getReplyCount();
        this.replies = board.getReplies();
        this.createdDate = board.getFormattedCreatedDate();
        this.modifyDate = board.getFormattedModifyDate();

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
        log.info("BoardToEntity로그 :" + writerDto.toEntity());
        return board;
    }
}