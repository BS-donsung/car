package com.imsi.car.domain.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/reply")
@RestController
@Log4j2
@RequiredArgsConstructor // final 필드를 가지고 생성자를 생성해주는 역할.
public class ReplyController {
    private final ReplyService rs;

    // 댓글쓰기 요청을 보내는 컨트롤러 (YARC 이용)
    @PostMapping("/post")
    public ResponseEntity<ReplyDto> createReply(@RequestBody ReplyDto replyDto) {
        log.info("댓글쓰기 요청 로그: {}", replyDto);
        rs.writeReply(replyDto);
        return ResponseEntity.ok(replyDto);
    }

    // 글 삭제 요청을 보내는 컨트롤러(YARC 이용)
    @DeleteMapping("/delete/{rno}")
    public void deleteReply(@PathVariable Long rno) {
        log.info("댓글삭제 요청 로그 : {}", rno);
        rs.deleteReply(rno);
    }

    // 글 수정 요청을 보내는 컨트롤러(YARC 이용)
    @PutMapping("/modify/{rno}")
    public ResponseEntity<Void> modifyReply(@PathVariable Long rno, @RequestBody ReplyDto replyDto) {
        rs.modifyReply(rno, replyDto);
        log.info("댓글수정 요청 로그 : {}", rno, replyDto);
        return ResponseEntity.ok().build();
    }

}