package com.imsi.car.domain.car.model;

import java.util.List;

import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spk;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car")
    private Car car;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "store")
    private List<StoreOption> storeOptions;
}