package com.imsi.car.domain.board.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imsi.car.domain.board.BoardUtils;
import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepo replyRepo;
    private final BoardRepo boardRepo;
    private final BoardUtils boardUtils;

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

    @Override
    public List<ReplyDto> listMyPage(String username, int page) {
        log.info("listMyPage : {}, {}",username,page);
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("rno").descending());
        List<Reply> replies = replyRepo.findByUsername(username, pageable);
        List<ReplyDto> replyDtos = boardUtils.replyListToDtos(replies);
        return replyDtos;
    }

} 
