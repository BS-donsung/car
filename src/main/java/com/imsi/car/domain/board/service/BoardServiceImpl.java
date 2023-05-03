package com.imsi.car.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.board.BoardUtils;
import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;

import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.user.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
    private final UserRepo userRepo;
    private final ReplyRepo replyRepo;
    private final BoardUtils boardUtils;

    @Override
    public List<BoardDto> listBoardPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("bno").descending());
        Page<Board> result = boardRepo.findAll(pageable);
        return boardUtils.boardListToDtos(result);
    }

    // 게시글 쓰기 요청을 수락하는 서비스
    @Transactional
    @Override
    public void writeBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardRepo.save(board);
    }
    // 게시글 수정
    @Override
    public void modifyBoard(int bno, BoardDto boardDto) {

    }



    // 게시글 삭제
    public void deleteBoard(int bno) {

    }

    @Override
    public BoardDto boardView(int bno) {
        Board board = boardRepo.findByBno(bno);
        BoardDto boardDto = new BoardDto(board);
        log.info("boardDto : {}", boardDto);
        return boardDto;
    }

    @Override
    public List<BoardDto> searchBoard(String keyword, int flag, int page) {

        return null;
    }

    @Override
    public BoardDto listMyPage(String username, int page) {
        return null;
    }

}