package com.imsi.car.domain.board.service;

import java.util.List;

import com.imsi.car.domain.board.dto.ReplyDto;

public interface ReplyService {



    public void writeReply(ReplyDto replyDto);

    public void deleteReply(int rno);

    public void modifyReply(ReplyDto ReplyDto);

    public List<ReplyDto> listMyPage(String username, int page);

}
