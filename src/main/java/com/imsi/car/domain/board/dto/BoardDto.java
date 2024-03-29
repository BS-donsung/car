package com.imsi.car.domain.board.dto;

import java.util.List;

import com.imsi.car.domain.board.BoardUtils;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.car.model.Store;
import com.imsi.car.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String nickname;
    private String createdDate;
    private String modifyDate;
    private List<ReplyDto> replyDtos;
    private Integer spk;
    private int type;
    private String thumbnail;


    public BoardDto(Board board) {
        BoardUtils BoardUtils = new BoardUtils();
        this.bno = board.getBno();
        this.username = board.getUser().getUsername();
        this.nickname = board.getUser().getNickname();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.likes = board.getLikes();
        this.replyCount = board.getReplyCount();
        this.createdDate = board.getFormattedCreatedDate();
        this.modifyDate = board.getFormattedModifyDate();
        this.replyDtos = BoardUtils.replyListToDtos(board.getReplies());
        this.type = board.getType();
        this.thumbnail = board.getThumbnail();
        this.spk = board.getStore()==null?0:board.getStore().getSpk();
    }

    public Board toEntity() {
        Board board = Board.builder()
                .bno(bno)
                .user(User.builder().username(username).nickname(nickname).build())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likes(likes)
                .replyCount(replyCount)
                .type(type)
                .thumbnail(thumbnail)
                .store(spk==0?null:Store.builder().spk(spk).build())
                .build();
        return board;
    }
}