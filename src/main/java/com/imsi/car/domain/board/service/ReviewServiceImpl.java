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

import com.imsi.car.domain.board.dto.ReviewDto;
import com.imsi.car.domain.board.model.Review;
import com.imsi.car.domain.board.repo.ReplyRepo;
import com.imsi.car.domain.board.repo.ReviewRepo;
import com.imsi.car.domain.car.model.Car;
import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final ReplyRepo replyRepo;

    @Override
    public List<ReviewDto> listReviewPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("rvno").descending());
        Page<Review> result = reviewRepo.findAll(pageable);
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : result) {
            ReviewDto reviewDto = ReviewDto.builder()
                    .rvno(review.getRvno())
                    .title(review.getTitle())
                    .content(review.getContent())
                    .writerDto(new UserDto(review.getWriter()))
                    .createdDate(review.getFormattedCreatedDate())
                    .modifyDate(review.getFormattedModifyDate())
                    .build();
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    // 리뷰 쓰기 요청을 수락하는 서비스
    @Override
    public void writeReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity();
        reviewRepo.save(review);
    }

    // 리뷰 수정
    @Override
    public void modifyReview(Long rvno, ReviewDto reviewDto) {
        Review review = reviewRepo.getById(rvno);
        review = review.toBuilder()
                .title(reviewDto.getTitle())
                .content(reviewDto.getContent())
                .build();
        reviewRepo.save(review);
    }

    // 리뷰 삭제
    public void deleteReview(Long rvno) {
        Review review = reviewRepo.getById(rvno);
        if (review != null) {
            reviewRepo.delete(reviewRepo.getById(rvno));
            // log.info("리뷰(rvno={})이 삭제되었습니다.", rvno);
        } else {
            // log.info("해당 rvno({})의 리뷰이 존재하지 않습니다.", rvno);
        }
    }

    // 리뷰 조회
    @Override
    public ReviewDto reviewView(Long rvno) {
        Review review = reviewRepo.getById(rvno);
        if (review != null) {
            review.addViewCount(); // addViewCount() 메소드를 호출하여 viewCount 필드를 증가시킴
            reviewRepo.save(review); // 변경된 review 객체를 저장
            return new ReviewDto(review);
        }
        return null;
    }

    // 에러 메시지 파훼 메소드(실제로는 사용하고 있어 문제가 없으나 컴파일러에서 인식이 되지 않는다고 함)
    @Override
    public List<Car> findByCarNameContainingAndCarIsNotNull(String keyword, Pageable pageable) {
        log.info("확인 로그", keyword, pageable);
        return reviewRepo.findByCarNameContainingAndCarIsNotNull(keyword, pageable);
    }

    @Override
    public List<ReviewDto> searchReview(String keyword, int flag, int page) {
        Pageable pageable = PageRequest.of(page - 1, 100, Sort.by("rvno").descending());
        List<Review> reviewList = new ArrayList<>();
        switch (flag) {
            case 1: // 제목 검색
                reviewList = reviewRepo.findByTitleContaining(keyword, pageable);
                break;
            case 2: // 내용 검색
                reviewList = reviewRepo.findByContentContaining(keyword, pageable);
                break;
            case 3: // 닉네임 검색
                List<User> userList = userRepo.findByNicknameContaining(keyword);
                reviewList = userList.stream()
                        .map(User::getReviews)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                break;
            case 4: // 댓글 검색
                reviewList = reviewRepo.findByRepliesTextContaining(keyword, pageable);
                break;
            case 5: // 차량명 검색
                List<Car> carList = reviewRepo.findByCarNameContainingAndCarIsNotNull(keyword, pageable);
                for (Car car : carList) {
                    List<Review> carReviews = car.getReviews();
                    reviewList.addAll(carReviews);
                }
                break;

            default:
                break;
        }
        return reviewList.stream().map(ReviewDto::new).collect(Collectors.toList());
    }

}