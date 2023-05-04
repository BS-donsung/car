package com.imsi.car.domain.car.repo_custom;

import com.imsi.car.domain.board.model.Board;

import jakarta.transaction.Transactional;

public interface BoardCustomRepo {
    @Transactional
    void saveBoard(Board board);
}
