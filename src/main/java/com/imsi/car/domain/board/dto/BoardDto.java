package com.imsi.car.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.user.dto.UserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// @Setter
@ToString
@Getter
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private int viewCount;
    private int likes;
    private List<Reply> replies;
    private UserDto writerDto;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.writerDto = new UserDto(board.getWriter());
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.likes = board.getLikes();
        this.createdDate = board.getCreatedDate();
        this.modifyDate = board.getModifyDate();
    }

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writerDto.toEntity())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content, UserDto writerDto, LocalDateTime createdDate, LocalDateTime modifyDate,int viewCount,int likes
    , List<Reply> replies
    ){
        this.id = id;
        this.writerDto = writerDto;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifyDate = modifyDate;
    }
}