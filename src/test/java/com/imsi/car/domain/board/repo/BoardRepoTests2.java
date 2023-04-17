package com.imsi.car.domain.board.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import com.imsi.car.domain.board.model.Board;
import com.imsi.car.domain.user.repo.UserRepo;

@SpringBootTest
public class BoardRepoTests2 {
    @Autowired
    private BoardRepo boardRepo;
    @Autowired
    private UserRepo userRepo;

    // @Test
    // void testFindByOrderByIdDesc() {
    //     List<Board> listBoards = boardRepo.findByOrderByIdDesc();

    //     for (int i = 0; i < listBoards.size(); i++) {
    //         System.out.println(">>>" + listBoards.get(i));
    //     }
    // }

    // @Test // 제목으로 검색하는 테스트
    // void testFindByTitleContaining() {
    //     List<Board> sTitle = boardRepo.findByTitleContaining("안녕하세요");

    //     for (int i = 0; i < sTitle.size(); i++) {
    //         System.out.println("제목 검색>>>" + sTitle.get(i));
    //     }
    // }

    // @Test // 글 내용으로 검색하는 테스트
    // void testFindByContentContaining() {
    //     List<Board> sContent = boardRepo.findByContentContaining("가나");
    //     for (int i = 0; i < sContent.size(); i++) {
    //         System.out.println("내용 검색>>>" + sContent.get(i));
    //     }
    // }

    // // 글쓴이로 검색하는 테스트
    // @Test
    // void testFindByNicknameContaining() {
    //     List<com.imsi.car.domain.user.model.User> sWriter = userRepo.findByNicknameContaining("유저3");
    //     for (int i = 0; i < sWriter.size(); i++) {
    //         System.out.println("글쓴이를 검색>>>" + sWriter.get(i));
    //         Long writerId = Long.valueOf(sWriter.get(i).getId());
    //         List<Board> sWriterB = boardRepo.findByWriterId(writerId);
    //         for (int j = 0; j < sWriterB.size(); j++) {
    //             System.out.println("글쓴이로 검색>>>" + sWriter.get(i) + "가 작성한 글: " + sWriterB.get(j));
    //         }
    //     }
    // }

    // @Test // 댓글내용으로 검색하는 테스트
    // void testFindByRepliesContaining() {
    //     List<Board> sReply = boardRepo.findByRepliesTextContaining("세 번째");

    //     for (int i = 0; i < sReply.size(); i++) {
    //         System.out.println("댓글 검색>>>" + sReply.get(i));
    //     }
    // }

    // @Test   //게시글을 10개 들고오는 테스트
    // @Query(value = "SELECT b FROM Board b ORDER BY b.id DESC LIMIT 10", nativeQuery = false)
    // void testFindLatestBoards() {
    //     List<Board> lateBoards = boardRepo.findLatestBoards(0, 10);
    //     for (int i = 0; i < lateBoards.size(); i++) {
    //         System.out.println(">>>" + lateBoards.get(i));
    //     }
    // }

    // @Test //게시글을 수정하는 테스트
    // void testUpdateBoard() {

    //     Board board = Board.builder()
    //             .id(14L) // 수정할 게시글의 ID
    //             .title("수정된 제목")
    //             .content("수정된 내용")
    //             .build();

    //     boardRepo.save(board);
    // }

    // @Test // 게시글을 삭제하는 테스트
    // void testDeleteBoard() {
    //     board
    // }

}
