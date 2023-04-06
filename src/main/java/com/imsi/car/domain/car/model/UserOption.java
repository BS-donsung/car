package com.imsi.car.domain.car.model;


import com.imsi.car.domain.user.model.User;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class UserOption {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uopk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private Car car; 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option")
    private Option option; // 옵션 이름
    private boolean isopt; // 옵션 적용 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
}
