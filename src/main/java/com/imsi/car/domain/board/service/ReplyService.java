package com.imsi.car.domain.board.service;

import com.imsi.car.domain.board.dto.ReplyDto;

public interface ReplyService {



    public void writeReply(ReplyDto replyDto);

    public void deleteReply(Long rno);

    public void modifyReply(Long rno, ReplyDto ReplyDto);

}
