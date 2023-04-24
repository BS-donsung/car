package com.imsi.car.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.imsi.car.domain.board.dto.ReviewDto;
import com.imsi.car.domain.car.model.Car;

public interface ReviewService {

    public ReviewDto reviewView(Long rvno);

    // page번째 게시물들 가져오기
    public List<ReviewDto> listReviewPage(int page);

    // 게시물 검색. 메소드 안에서 댓글,제목,내용,아이디 검색 모두 구현
    public List<ReviewDto> searchReview(String keyword, int flag, int page);

    // 리뷰 작성
    public void writeReview(ReviewDto reviewDto);

    // 리뷰 삭제
    public void deleteReview(Long bno);

    // 리뷰 수정
    public void modifyReview(Long rvno, ReviewDto reviewDto);

    List<Car> findByCarNameContainingAndCarIsNotNull(String keyword, Pageable pageable);

}
