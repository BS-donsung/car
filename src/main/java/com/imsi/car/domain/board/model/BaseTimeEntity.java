package com.imsi.car.domain.board.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class BaseTimeEntity {
    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;

    /* 해당 엔티티를 저장하기 이전에 실행 */
    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now();
        this.modifyDate = this.createdDate;
    }

    /* 해당 엔티티를 업데이트 하기 이전에 실행 */
    @PreUpdate
    public void onPreUpdate() {
        this.modifyDate = LocalDateTime.now();
    }

    /* 생성일을 지정한 형식으로 반환하는 메서드 */
    public String getFormattedCreatedDate() {
        return createdDate != null ? createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : "";
    }

    /* 수정일을 지정한 형식으로 반환하는 메서드 */
    public String getFormattedModifyDate() {
        return modifyDate != null ? modifyDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : "";
    }

}