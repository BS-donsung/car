package com.imsi.car.domain.board.service;

import com.imsi.car.domain.board.dto.ReplyDto;

public interface ReplyService {

    // public ReplyDto ReplyCount(Long rno);

    public void writeReply(ReplyDto replyDto);

    // public void writeReviewReply(ReplyDto replyDto);

    public void deleteReply(Long rno);

    public void modifyReply(Long rno, ReplyDto ReplyDto);

}
