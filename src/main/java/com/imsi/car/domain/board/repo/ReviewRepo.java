package com.imsi.car.domain.board.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.car.model.Car;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    // 리뷰 삭제
    void delete(Review review);

    // 내림차순 검색
    List<Review> findByOrderByRvnoDesc();

    // 제목으로 검색
    List<Review> findByTitleContaining(String keyword, Pageable pageable);

    // 내용으로 검색
    List<Review> findByContentContaining(String keyword, Pageable pageable);

    // 댓글 내용으로 검색
    List<Review> findByRepliesTextContaining(String keyword, Pageable pageable);

    // 닉네임 검색(글쓴이 정보 검색)
    @Query(value = "select r from Review r where r.writer.nickname = :nickname", nativeQuery = false)
    List<Review> getReviewListByNickname(@Param("nickname") String nickname);

    // 위에서 검색한 정보를 바탕으로writer로 검색
    List<Review> findByWriterUsername(String username, Pageable pageable);

    // 차량명으로 검색
    List<Car> findByCarNameContainingAndCarIsNotNull(String keyword, Pageable pageable);

    // 이메일 검색(미사용)
    @Query(value = "select r from Review r where r.writer.email = :email", nativeQuery = false)
    List<Review> getReviewListByEmail(@Param("email") String email);

    // 최근 리뷰 10개 보기
    @Query(value = "SELECT r FROM Review r ORDER BY r.rvno DESC LIMIT 10", nativeQuery = false)
    List<Review> findLatestReviews(@Param("default_Index") int default_Index,
            @Param("pageSize") int pageSize);

    // 리뷰을 수정하는 기능
    @Modifying
    @Query("UPDATE Review r SET r.title = :title, r.content = :content WHERE r.rvno = :rvno")
    void updateReview(@Param("rvno") Long rvno, @Param("title") String title, @Param("content") String content);

    // List<Car> findByCarNameContaining(String keyword, Pageable pageable);

}