package com.imsi.car.domain.board.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
    private final UserRepo userRepo;
    private final ReplyRepo replyRepo;

    // public Map<Long, Integer> getReplyCountByBno() {
    // List<Object[]> result = replyRepo.countRepliesByBno();
    // Map<Long, Integer> replyCountByBno = new HashMap<>();
    // for (Object[] row : result) {
    // Long bno = (Long) row[0];
    // Long replyCount = (Long) row[1];
    // replyCountByBno.put(bno, replyCount.intValue());
    // }
    // return replyCountByBno;
    // }

    @Override
    public List<BoardDto> listBoardPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepo.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : result) {
            BoardDto boardDto = BoardDto.builder()
                    .bno(board.getBno())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writerDto(new UserDto(board.getWriter()))
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    // 게시글 쓰기 요청을 수락하는 서비스
    @Override
    public void writeBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardRepo.save(board);
    }

    @Override
    public void writeReview(Review review) {
        // TODO Auto-generated method stub

    }

    // 게시글 수정
    @Override
    public void modifyBoard(Long bno, BoardDto boardDto) {
        Board board = boardRepo.getById(bno);
        board = board.toBuilder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();
        boardRepo.save(board);
    }

    @Override
    public void modifyReview(Review reivew) {
        // TODO Auto-generated method stub

    }

    // 게시글 삭제
    public void deleteBoard(Long bno) {
        Board board = boardRepo.getById(bno);
        if (board != null) {
            boardRepo.delete(boardRepo.getById(bno));
            // log.info("게시글(bno={})이 삭제되었습니다.", bno);
        } else {
            // log.info("해당 bno({})의 게시글이 존재하지 않습니다.", bno);
        }
    }

    @Override
    public void deleteReview(Review review) {
        // TODO Auto-generated method stub

    }

    // 게시글 조회
    @Override
    public BoardDto BoardView(Long bno) {
        Board board = boardRepo.getById(bno);
        if (board != null) {
            board.addViewCount(); // addViewCount() 메소드를 호출하여 viewCount 필드를 증가시킴
            boardRepo.save(board); // 변경된 board 객체를 저장
            return new BoardDto(board);
        }
        return null;
    }

    @Override
    public List<BoardDto> searchBoard(String keyword, int flag, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("bno").descending());
        List<Board> boardList = new ArrayList<>();
        switch (flag) {
            case 1: // 제목 검색
                boardList = boardRepo.findByTitleContaining(keyword, pageable);
                break;
            case 2: // 내용 검색
                boardList = boardRepo.findByContentContaining(keyword, pageable);
                break;
            case 3: // 닉네임 검색
                List<User> userList = userRepo.findByNicknameContaining(keyword);
                boardList = userList.stream()
                        .map(User::getBoards)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                break;
            case 4: // 댓글 검색
                boardList = boardRepo.findByRepliesTextContaining(keyword, pageable);
                break;
            default:
                break;
        }
        return boardList.stream().map(BoardDto::new).collect(Collectors.toList());
    }

}