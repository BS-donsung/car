package com.imsi.car.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.board.repo.ReviewRepo;
import com.imsi.car.domain.user.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepo replyRepo;
    private final BoardRepo boardRepo;
    private final ReviewRepo reviewRepo;

    // 댓글 쓰기
    @Override
    public void writeReply(ReplyDto replyDto) { // writeReply가 호출되었을 때
        replyDto.builder()
                .username(replyDto.getUsername())
                .bno(replyDto.getBno())
                .rvno(replyDto.getRvno())
                .build();
        Reply reply = replyDto.toEntity();
        replyRepo.save(reply);
        if (reply.getBoard() != null) {
            Board board = boardRepo.getById(reply.getBoard().getBno());
            board.addReplyCount();
            boardRepo.save(board);
            log.info("보드리플 카운트 업 로그");
        } else if (reply.getReview() != null) {
            Review review = reviewRepo.getById(reply.getReview().getRvno());
            review.addReplyCount();
            reviewRepo.save(review);
            log.info("리뷰리플 카운트 업 로그");
        }
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
        if (reply.getBoard() != null) {
            Board board = boardRepo.getById(reply.getBoard().getBno());
            board.subtractReplyCount();
            boardRepo.save(board);
            replyRepo.delete(reply);
            log.info("보드리플 카운트 다운 로그");
            log.info("댓삭:댓글(rno={})이 삭제되었습니다.", rno);
        } else if (reply.getReview() != null) {
            Review review = reviewRepo.getById(reply.getReview().getRvno());
            review.subtractReplyCount();
            reviewRepo.save(review);
            log.info("리뷰리플 카운트 다운 로그");
            log.info("댓삭:댓글(rno={})이 삭제되었습니다.", rno);
        } else {
            log.info("댓삭:댓글 rno({})을 쓴 게시글이 존재하지 않습니다.", rno);
        }
    }

}
