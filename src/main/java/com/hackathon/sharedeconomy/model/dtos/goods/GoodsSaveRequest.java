package com.hackathon.sharedeconomy.model.dtos.goods;

import com.hackathon.sharedeconomy.model.entity.Goods;
import com.hackathon.sharedeconomy.model.entity.GoodsImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GoodsSaveRequest {
    // 제목
    private String title;

    // 보증금
    private Long deposit;

    // 월세
    private Long monthlyRent;

    // 방 개수
    private int roomCnt;

    // 모집 인원 수
    private int peopleCnt;

    // 시
    private String city;

    // 구
    private String gu;

    // 동
    private String dong;

    // 상세주소
    private String detailsAddress;

    @Builder
    public GoodsSaveRequest(String title, Long deposit, Long monthlyRent, int roomCnt, int peopleCnt, String city, String gu, String dong, String detailsAddress) {
        this.title = title;
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.roomCnt = roomCnt;
        this.peopleCnt = peopleCnt;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.detailsAddress = detailsAddress;
    }

    public Goods ToEntity(List<GoodsImage> imageLIst){
        return Goods.builder()
                .title(this.title)
                .deposit(this.deposit)
                .monthlyRent(this.monthlyRent)
                .roomCnt(this.roomCnt)
                .peopleCnt(this.peopleCnt)
                .city(this.city)
                .gu(this.gu)
                .dong(this.dong)
                .detailsAddress(this.detailsAddress)
                .images(imageLIst)
                .build();
    }
}
