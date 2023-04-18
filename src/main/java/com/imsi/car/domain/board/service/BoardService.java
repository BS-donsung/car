package com.imsi.car.domain.board.service;

import java.util.List;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Review;

public interface BoardService {

    public BoardDto BoardView(Long bno);

    // page번째 게시물들 가져오기
    public List<BoardDto> listBoardPage(int page);

    // 게시물 검색. 메소드 안에서 댓글,제목,내용,아이디 검색 모두 구현
    public List<BoardDto> searchBoard(String keyword, int flag, int page);

    // board 작성
    public void writeBoard(BoardDto boardDto);

    // board 삭제
    public void deleteBoard(Long bno);

    // board 수정
    public void modifyBoard(Long bno, BoardDto boardDto);

    // review 작성
    public void writeReview(Review review);

    // review 삭제
    public void deleteReview(Review review);

    // review 수정
    public void modifyReview(Review reivew);

}
