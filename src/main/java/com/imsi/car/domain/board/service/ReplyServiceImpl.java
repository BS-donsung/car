package com.imsi.car.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
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

    // 댓글 쓰기
    @Override
    public void writeReply(ReplyDto replyDto) { // writeReply가 호출되었을 때
        Reply reply = replyDto.toEntity();
        replyRepo.save(reply);
    }

    @Override
    public void modifyReply(ReplyDto replyDto) {
        Reply reply = replyDto.toEntity();
        replyRepo.save(reply);
    }

    @Override
    public void deleteReply(int rno) {
        replyRepo.deleteById((long)rno);
    }

}
