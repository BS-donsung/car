package com.imsi.car.domain.board;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Review;

public interface BoardService {

    // TODO : DG page번째 게시물들 가져오기
    public void listBoardPage(int page,int imsi);

    // TODO : DG board 저장
    public void writeBoard(Board board);

    // TODO : DG board 삭제
    public void deleteBoard(Board board);

    // TODO : DG board 수정
    public void modifyBoard(Board board);

    // TODO : DG review 작성
    public void writeReview(Review review);
    
    // TODO : DG review 삭제
    public void deleteReview(Review review);

    // TODO : DG review 수정
    public void modifyReview(Review reivew);

    // TODO : DG 게시물 검색. 메소드 안에서 댓글,제목,내용,아이디 검색 모두 구현
    public void searchBoard(String keyword, int flag);
}
