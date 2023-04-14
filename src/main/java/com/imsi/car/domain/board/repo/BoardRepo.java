package com.imsi.car.domain.board.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.board.model.Board;

public interface BoardRepo extends JpaRepository<Board, Long> {
    //게시글 삭제
    void delete(Board board);

    //내림차순 검색
    List<Board> findByOrderByIdDesc();
    //제목으로 검색
    List<Board> findByTitleContaining(String keyword);
    //내용으로 검색
    List<Board> findByContentContaining(String keyword);
    //댓글 내용으로 검색
    List<Board> findByRepliesTextContaining(String keyword);
    //글쓴이로 검색 
        //닉네임 검색(글쓴이 정보 검색)
        @Query(value = "select b from Board b where b.writer.nickname = :nickname", nativeQuery = false)
        List<Board> getBoardListByNickname(@Param("nickname") String nickname);
            //검색한 정보를 바탕으로writer로 검색
            List<Board> findByWriterId(Long writer);

    //게시글 상세보기
    // Optional<Board> findById(Long id);
    //보드를 삭제하는 기능
    // List<Board>delete(Long id);

    //이메일 검색(미사용)
    @Query(value = "select b from Board b where b.writer.email = :email", nativeQuery = false)
    List<Board> getBoardListByEmail(@Param("email") String email);

    // 최근 게시글 10개 보기
    @Query(value = "SELECT b FROM Board b ORDER BY b.id DESC LIMIT 10", nativeQuery = false)
    List<Board> findLatestBoards(@Param("default_Index") int default_Index, @Param("pageSize") int pageSize);

    // 게시글을 수정하는 기능
    @Modifying
    @Query("UPDATE Board b SET b.title = :title, b.content = :content WHERE b.id = :id")
    void updateBoard(@Param("id") Long id, @Param("title") String title, @Param("content") String content);

    // 게시글 조회 시 조회수를 증가시키는 메서드
    @Modifying
    @Query("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.id = :id")
    void increaseViewCount(@Param("id") Long id);

}