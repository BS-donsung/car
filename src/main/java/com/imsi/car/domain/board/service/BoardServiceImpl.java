package com.imsi.car.domain.board.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.dto.ReplyDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.model.Reply;
import com.imsi.car.domain.board.repo.BoardRepo;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
    private final UserRepo userRepo;
    private final ReplyRepo replyRepo;

    @Override
    public List<BoardDto> listBoardPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("bno").descending());
        Page<Board> result = boardRepo.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : result) {
            BoardDto boardDto = BoardDto.builder()
                    .bno(board.getBno())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writerDto(new UserDto(board.getWriter()))
                    .replyCount(board.getReplyCount())
                    .viewCount(board.getViewCount())
                    .createdDate(board.getFormattedCreatedDate())
                    .modifyDate(board.getFormattedModifyDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
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
    public void modifyBoard(Long bno, BoardDto boardDto) {
        Board board = boardRepo.getById(bno);
        board = board.toBuilder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();
        boardRepo.save(board);
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

    // 게시글 조회
    @Override
    public BoardDto BoardView(Long bno) {
        Board board = boardRepo.getById(bno); // bno 필드를 이용하여 해당 게시글을 조회
        if (board != null) {
            board.addViewCount(); // addViewCount() 메소드를 호출하여 viewCount 필드를 증가시킴
            boardRepo.save(board); // 변경된 board 객체를 저장
            return new BoardDto(board);
        }
        return null;
    }

    @Override
    public List<BoardDto> searchBoard(String keyword, int flag, int page) {
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("bno").descending());
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
            case 5: // 해당 닉네임이 쓴 댓글 보기 //1 닉네임 찾기, 2, 해당 닉네임이 쓴 댓글이 있는 글 전부출력하기
                List<Reply> replyList = replyRepo.findAll()
                        .stream()
                        .filter(reply -> reply.getUser().getNickname().contains(keyword))// equals(keyword)를 사용하면 정확한
                                                                                         // 검색결과만 걸러낼 수 있음
                        .collect(Collectors.toList()); // 리스트에 담음
                boardList.clear(); // 보드 중복을 방지하기 위한 클리어
                boardList.addAll(replyList.stream()
                        .map(Reply::getBoard)// Reply에서 Board를 get함
                        .distinct() // 중복 게시글 쳐내는 메서드
                        .collect(Collectors.toList())); // 콜렉터 메소드로 보드리스트에 추가함
                break;

            default:
                break;
        }
        return boardList.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Override
    public BoardDto listMyPage(String username, int page) {
        // 게시글 페이지 조회
        Pageable boardPageable = PageRequest.of(page - 1, 10, Sort.by("bno").descending());
        List<Board> boardPage = boardRepo.findByWriterUsername(username, boardPageable);
        List<BoardDto> boardDtos = boardPage.stream()
                .map(board -> BoardDto.builder()
                        .bno(board.getBno())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writerDto(new UserDto(board.getWriter()))
                        .createdDate(board.getFormattedCreatedDate())
                        .modifyDate(board.getFormattedModifyDate())
                        .build())
                .collect(Collectors.toList());

        // 댓글 페이지 조회
        Pageable replyPageable = PageRequest.of(page - 1, 10, Sort.by("rno").descending());
        Page<Reply> replyPage = replyRepo.findByUserUsername(username, replyPageable);
        List<ReplyDto> replyDtos = replyPage.stream()
                .map(reply -> ReplyDto.builder()
                        .rno(reply.getRno())
                        .text(reply.getText())
                        .build())
                .collect(Collectors.toList());

        // BoardDto 객체 생성
        BoardDto boardDto = BoardDto.builder()
                .boardDtos(boardDtos)
                .replyDtos(replyDtos)
                .build();

        return boardDto;
    }

}