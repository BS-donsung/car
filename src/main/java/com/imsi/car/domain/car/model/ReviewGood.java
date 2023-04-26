package com.imsi.car.domain.car.model;

import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rgid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rid")
    private Review car;
    private boolean like;

}
