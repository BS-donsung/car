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

import com.imsi.car.domain.board.dto.ReviewDto;
import com.imsi.car.domain.board.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/review")
@RestController
@Log4j2
@RequiredArgsConstructor // final 필드를 가지고 생성자를 생성해주는 역할.
public class ReviewController {

    private final ReviewService rs;

    /* 게시글 목록 출력 */
    @GetMapping("")
    // http://localhost:9000/review?page=2와 같은 식으로 찍어준다.
    public List<ReviewDto> getReviewlist(@RequestParam(defaultValue = "1") Integer page) {
        log.info("페이징 로그 : {}", page);
        List<ReviewDto> reviewDtoList = rs.listReviewPage(page);
        return reviewDtoList;
    }

    /* 게시글 상세 보기 */
    @GetMapping("/view/{rvno}")
    public ReviewDto ReviewView(@PathVariable Long rvno) {
        log.info("게시글 상세보기 로그: {}", rvno);
        return rs.ReviewView(rvno);
    }

    // 글쓰기 요청을 보내는 컨트롤러 (YARC 이용)
    @PostMapping("/post")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        log.info("글쓰기 요청 로그: {}", reviewDto);
        rs.writeReview(reviewDto);
        return ResponseEntity.ok(reviewDto);
    }

    // 글 삭제 요청을 보내는 컨트롤러(YARC 이용)
    @DeleteMapping("/delete/{rvno}")
    public void deleteReview(@PathVariable Long rvno) {
        log.info("글삭제 요청 로그", rvno);
        rs.deleteReview(rvno);
    }

    // 글 수정 요청을 보내는 컨트롤러(YARC 이용)
    @PutMapping("/modify/{rvno}")
    public ResponseEntity<Void> modifyReview(@PathVariable Long rvno, @RequestBody ReviewDto reviewDto) {
        log.info("글수정 요청 로그: {}", rvno, reviewDto);
        rs.modifyReview(rvno, reviewDto);
        return ResponseEntity.ok().build();
    }

    // 검색한 게시글 목록 출력(flag값 1=제목,2=내용,3=글쓴이,4=덧글내용 검색)
    @GetMapping("/search/{flag}/{keyword}")
    public List<ReviewDto> searchReview(@PathVariable String keyword, @PathVariable int flag,
            @RequestParam(defaultValue = "1") Integer page) {
        log.info("검색 로그(제목,내용,쓰니,댓글): {}", flag, keyword);
        return rs.searchReview(keyword, flag, page);
    }
}