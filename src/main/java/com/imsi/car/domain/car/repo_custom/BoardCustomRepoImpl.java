package com.imsi.car.domain.car.repo_custom;

import com.imsi.car.domain.board.model.Board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BoardCustomRepoImpl implements BoardCustomRepo{

    private final EntityManager em;
    @Override
    public void saveBoard(Board board) {
        
    }
    
}
