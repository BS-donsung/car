package com.imsi.car.domain.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarOption {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option")
    private Option option; // 옵션 이름
    private boolean opt; // 옵션 적용 여부
    private int cost;

    
    @Override
    public String toString() {
        return "option : "+this.option+", isopt : "+this.opt + "cost : "+this.cost+"\n";
    }
}
