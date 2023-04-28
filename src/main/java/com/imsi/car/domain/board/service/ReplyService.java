package com.imsi.car.domain.board.service;

import com.imsi.car.domain.board.dto.ReplyDto;

public interface ReplyService {



    public void writeReply(ReplyDto replyDto);

    public void deleteReply(int rno);

    public void modifyReply(ReplyDto ReplyDto);

}
