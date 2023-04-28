package com.imsi.car.domain.board.service;

import java.util.List;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;


public interface BoardService {

    public BoardDto boardView(int bno);

    // page번째 게시물들 가져오기
    public List<BoardDto> listBoardPage(int page);

    // 게시물 검색. 메소드 안에서 댓글,제목,내용,아이디 검색 모두 구현
    public List<BoardDto> searchBoard(String keyword, int flag, int page);

    // board 작성
    public void writeBoard(BoardDto boardDto);

    // board 삭제
    public void deleteBoard(int bno);

    // board 수정
    public void modifyBoard(int bno, BoardDto boardDto);

    // public List<BoardDto> listMyPage(int page);
    public List<BoardDto> listMyPage(String username, int page);


}
