package com.imsi.car.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imsi.car.domain.board.dto.BoardDto;
import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.board.service.BoardService;
import com.imsi.car.domain.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/board")
@RestController
@Log4j2
@RequiredArgsConstructor // final 필드를 가지고 생성자를 생성해주는 역할.
public class BoardController {

    private final BoardService boardService;

    /* 게시글 목록 출력 */
    @GetMapping("")
    // http://localhost:9000/board?page=2와 같은 식으로 찍어준다.
    public List<BoardDto> getBoardlist(@RequestParam(defaultValue = "1") Integer page) {
        log.info("페이징 로그 : {}", page);
        List<BoardDto> boardDtoList = boardService.listBoardPage(page);
        return boardDtoList;
    }

    /* 게시글 상세 보기 */
    @GetMapping("/view/{bno}")
    public BoardDto boardView(@PathVariable int bno) {
        log.info("/board/view{} : ", bno);
        return boardService.boardView(bno);
    }



    @PostMapping("/post")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto, HttpServletRequest req) {
        String username = (String) req.getAttribute("username");
        boardDto.setUsername(username);
        log.info("/board/post: {}", boardDto);

        boardService.writeBoard(boardDto);
        return ResponseEntity.ok(boardDto);
    }


    // 글 삭제 요청을 보내는 컨트롤러(YARC 이용)
    @DeleteMapping("/delete/{bno}")
    public void deleteBoard(@PathVariable int bno) {
        log.info("글삭제 요청 로그", bno);
        boardService.deleteBoard(bno);
    }

    // 글 수정 요청을 보내는 컨트롤러(YARC 이용)
    @PutMapping("/modify/{bno}")
    public ResponseEntity<Void> modifyBoard(@PathVariable int bno, @RequestBody BoardDto boardDto) {
        log.info("글수정 요청 로그: {}", bno, boardDto);
        boardService.modifyBoard(bno, boardDto);
        return ResponseEntity.ok().build();
    }

    // 검색한 게시글 목록 출력(flag값 1=제목,2=내용,3=글쓴이(닉네임),4=댓글내용으로 검색,5쓰니의 댓글 검색)
    @GetMapping("/search/{flag}/{keyword}")
    public List<BoardDto> searchBoard(@PathVariable String keyword, @PathVariable int flag,
            @RequestParam(defaultValue = "1") Integer page) {
        log.info("검색 로그(제목,내용,쓰니,댓글,쓰니(댓글)): {}", flag, keyword);
        return boardService.searchBoard(keyword, flag, page);
    }

    // 마이 페이지(내가 쓴 최근 게시글 10개, 최근 댓글 10개를 보여줄 수 있다(페이징 처리))
    @GetMapping("/mypage")
    public List<BoardDto> getListMyPage(@RequestParam(defaultValue = "1") Integer page, HttpServletRequest req) {
        String username = (String) req.getAttribute("username");
        log.info("마이페이지 로그 : {}", username, page);
        List<BoardDto> boardDtos = boardService.listMyPage(username, page);
        log.info("boardDtos : {}",boardDtos);
        return boardDtos;
    }

}
