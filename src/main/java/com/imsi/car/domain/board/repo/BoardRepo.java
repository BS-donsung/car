package com.imsi.car.domain.board.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.board.model.Board;

public interface BoardRepo extends JpaRepository<Board, Long> {

    Board findByBno(int bno);

    // 게시글 삭제
    void delete(Board board);

    @Query(value = "select b from Board b")
    List<Board> findAllPage(Pageable pageable);

    // 내림차순 검색
    List<Board> findByOrderByBnoDesc();

    // 제목으로 검색
    List<Board> findByTitleContaining(String keyword, Pageable pageable);

    // 내용으로 검색
    List<Board> findByContentContaining(String keyword, Pageable pageable);

    // 댓글 내용으로 검색
    List<Board> findByRepliesTextContaining(String keyword, Pageable pageable);

    // 닉네임 검색(글쓴이 정보 검색)
    @Query(value = "select b from Board b where b.user.nickname = :nickname", nativeQuery = false)
    List<Board> getBoardListByNickname(@Param("nickname") String nickname);

    // 위에서 검색한 정보를 바탕으로username로 검색
    @Query(value = "select b from Board b where b.user.username = :username", nativeQuery = false)
    List<Board> findByUsername(String username, Pageable pageable);

    // 이메일 검색(미사용)
    @Query(value = "select b from Board b where b.user.email = :email", nativeQuery = false)
    List<Board> getBoardListByEmail(@Param("email") String email);

    // 최근 게시글 10개 보기
    @Query(value = "SELECT b FROM Board b ORDER BY b.bno DESC LIMIT 10", nativeQuery = false)
    List<Board> findLatestBoards(@Param("default_Index") int default_Index, @Param("pageSize") int pageSize);

    // 게시글을 수정하는 기능
    @Modifying
    @Query("UPDATE Board b SET b.title = :title, b.content = :content WHERE b.bno = :bno")
    void updateBoard(@Param("bno") Long bno, @Param("title") String title, @Param("content") String content);

}