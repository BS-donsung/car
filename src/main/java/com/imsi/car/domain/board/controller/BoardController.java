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
import com.imsi.car.domain.board.service.BoardServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/board")
@RestController
@Log4j2
@RequiredArgsConstructor // final 필드를 가지고 생성자를 생성해주는 역할.
public class BoardController {

    private final BoardServiceImpl bsi;

    /* 게시글 목록 출력 */
    @GetMapping("")
    // http://localhost:8180/board?page=2와 같은 식으로 찍어준다.
    public List<BoardDto> getBoardlist(@RequestParam(defaultValue = "1") Integer page) {
        log.info("page : {}",page);
        List<BoardDto> boardDtoList = bsi.listBoardPage(page);
        return boardDtoList;
    }
    /* 게시글 상세 보기*/
    @GetMapping("/view/{id}")
    public List<BoardDto> BoardView( @PathVariable Long id) {
        return bsi.BoardView(id);
    }
    // 글쓰기 요청을 보내는 컨트롤러 (YARC 이용)
    @PostMapping("/post")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        bsi.writeBoard(board);
        return ResponseEntity.ok(board);
    }

    // 글 삭제 요청을 보내는 컨트롤러(YARC 이용)
    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Long id) {
        log.info("/board/delete/{id} : {}",id);
        bsi.deleteBoard(id);
    }

    // 글 수정 요청을 보내는 컨트롤러(YARC 이용)
    @PutMapping("/modify/{id}")
    public ResponseEntity<Void> modifyBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        bsi.modifyBoard(id, boardDto);
        return ResponseEntity.ok().build();
    }

    // 검색한 게시글 목록 출력(flag값 1=제목,2=내용,3=글쓴이,4=덧글내용 검색)
    @GetMapping("/search/{flag}/{keyword}")
    public List<BoardDto> searchBoard(@PathVariable int flag, @PathVariable String keyword) {
        return bsi.searchBoard(keyword, flag);
    }

}