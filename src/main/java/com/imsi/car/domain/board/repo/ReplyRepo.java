package com.imsi.car.domain.board.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.board.model.Reply;

public interface ReplyRepo extends JpaRepository<Reply, Long> {

    List<Reply> getByParent(Long parent);

    // 댓글 삭제
    void delete(Reply reply);

    // 댓글을 수정하는 기능
    @Modifying
    @Query("UPDATE Reply r SET r.text = :text WHERE r.rno = :rno")
    void modifyReply(@Param("rno") Long rno, @Param("text") String text);

    @Query(value = "select r from Reply r where r.user.username = :username", nativeQuery = false)
    List<Reply> findByUsername(String username, Pageable pageable);

}
