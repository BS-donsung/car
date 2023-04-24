package com.imsi.car.domain.board.service;


import java.util.List;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;

public interface BoardService {

    public BoardDto BoardView(Long id);

    // TODO : DG page번째 게시물들 가져오기
    public List<BoardDto> listBoardPage(int page);
    
    // TODO : DG 게시물 검색. 메소드 안에서 댓글,제목,내용,아이디 검색 모두 구현
    public List<BoardDto> searchBoard(String keyword, int flag, int page);
    // TODO : DG board 저장
    public void writeBoard(Board board);

    // TODO : DG board 삭제
    public void deleteBoard(Long id);

    // TODO : DG board 수정
    public void modifyBoard(Long id, BoardDto boardDto);


}
