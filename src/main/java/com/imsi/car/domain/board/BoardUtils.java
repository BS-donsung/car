package com.imsi.car.domain.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;

@Component
public class BoardUtils {
    
    public List<BoardDto> boardListToDtos(List<Board> boards){
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board : boards){
            boardDtos.add(new BoardDto(board));
        }
        return boardDtos;
    }

    public List<ReplyDto> replyListToDtos(List<Reply> replies){
        List<ReplyDto> replyDtos = new ArrayList<>();
        for(Reply reply : replies){
            replyDtos.add(new ReplyDto(reply));
        }
        return replyDtos;
    }
}
