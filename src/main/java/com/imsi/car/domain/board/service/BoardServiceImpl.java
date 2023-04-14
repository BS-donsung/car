package com.imsi.car.domain.board.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
    private final UserRepo userRepo;

    @Override
    public List<BoardDto> listBoardPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        Page<Board> result = boardRepo.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : result) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writerDto(new UserDto(board.getWriter()))
                    // this.writerDto = new UserDto(board.getWriter());
                    // .createdDate(board.getCreatedDate())
                    // .modifiedDate(board.getModifiedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }



    // 게시글 쓰기
    @Override
    public void writeBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public void writeReview(Review review) {
        // TODO Auto-generated method stub

    }

    // 게시글 수정
    @Override
    public void modifyBoard(Long id, BoardDto boardDto) {
        Board board = boardRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
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
    public void deleteBoard(Long id) {
        Optional<Board> board = boardRepo.findById(id);
        if (board.isPresent()) {
            boardRepo.delete(board.get());
            log.info("게시글(id={})이 삭제되었습니다.", id);
        } else {
            log.info("해당 ID({})의 게시글이 존재하지 않습니다.", id);
        }
    }
    
    
    

    @Override
    public void deleteReview(Review review) {
        // TODO Auto-generated method stub

    }

    // 게시글 조회
    @Override
    public List<BoardDto> BoardView(long id) {
        Optional<Board> boardList = boardRepo.findById(id);
        if (boardList.isPresent()) {
            Board board = boardList.get();
            board.addViewCount(); // addViewCount() 메소드를 호출하여 viewCount 필드를 증가시킴
            boardRepo.save(board); // 변경된 board 객체를 저장
            return Arrays.asList(new BoardDto(board));
        }
        return Collections.emptyList();
    }

    // 게시글 검색
    @Override
    public List<BoardDto> searchBoard(String keyword, int flag) {
        List<Board> boardList = new ArrayList<>();
        switch (flag) {
            case 1: // 제목 검색
                boardList = boardRepo.findByTitleContaining(keyword);
                break;
            case 2: // 내용 검색
                boardList = boardRepo.findByContentContaining(keyword);
                break;
            case 3: // 닉네임 검색
                List<User> userList = userRepo.findByNicknameContaining(keyword);
                boardList = userList.stream()
                        .map(User::getBoards)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                break;
            case 4: // 댓글 검색
                boardList = boardRepo.findByRepliesTextContaining(keyword);
                break;
            default:
                break;
        }
        return boardList.stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }
}