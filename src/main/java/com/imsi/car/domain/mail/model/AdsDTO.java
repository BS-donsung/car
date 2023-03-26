package com.imsi.car.domain.mail.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AdsDTO {
    public final String title;
    public final String msg;
}
