package com.imsi.car.domain.car;

public interface CarService {
    
    // TODO : DG 차량 Brand 리스트
    public void listBrand();

    // TODO : DG Brand에 따른 차량 리스트
    public void listCarByBrand();

    // TODO : DG 차량 급 리스트
    public void listSegment();

    // TODO : DG 급에 따른 차량 리스트
    public void listCarBySegment();

    // TODO : DG 차량 정보 받아오기
    public void carInfo();
}
