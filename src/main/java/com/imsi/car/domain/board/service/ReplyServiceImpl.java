package com.imsi.car.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepo replyRepo;
    private final UserRepo userRepo;
    private final BoardRepo boardRepo;

    // 댓글 쓰기
    // 댓글 갯수를 알아낼 방법을 생각하자

    @Override
    public void writeReply(ReplyDto replyDto) {
        Reply reply = replyDto.toEntity();
        replyRepo.save(reply);
    }

    // 댓글 수정
    @Override
    public void modifyReply(Long rno, ReplyDto replyDto) {
        Reply reply = replyRepo.getById(rno);
        reply = reply.toBuilder()
                .text(replyDto.getText())
                .build();
        replyRepo.save(reply);
    }

    // 댓글 삭제
    public void deleteReply(Long rno) {
        Reply reply = replyRepo.getById(rno);
        if (reply != null) {
            replyRepo.delete(replyRepo.getById(rno));
            // Board board = reply.getBoard();
            // board = board.toBuilder()
            // .replyCount(board.getReplyCount() - 1)
            // .build();
            // boardRepo.save(board);
            // log.info("게시글(rno={})이 삭제되었습니다.", rno);
        } else {
            // log.info("해당 rno({})의 게시글이 존재하지 않습니다.", rno);
        }
    }
}
