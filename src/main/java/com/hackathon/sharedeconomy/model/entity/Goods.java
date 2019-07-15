package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;
import com.hackathon.sharedeconomy.model.enums.SaleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "for_sale_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // 매물 상태
    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    // 판매자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // 이미지
    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GoodsImage> images = new ArrayList<>();

    // 찜한 목록
    @OneToMany(mappedBy = "forSale")
    @JsonManagedReference
    private List<Shopping> shoppings = new ArrayList<>();

    @Builder
    public Goods(String title, Long deposit, Long monthlyRent, int roomCnt, int peopleCnt, String city, String gu, String dong, String detailsAddress, SaleType saleType, User user, List<GoodsImage> images, List<Shopping> shoppings) {
        this.title = title;
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.roomCnt = roomCnt;
        this.peopleCnt = peopleCnt;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.detailsAddress = detailsAddress;
        this.saleType = saleType;
        this.user = user;
        this.images = images;
        this.shoppings = shoppings;
    }

    public void updateForSale(ForSaleDto.Save saveDto) {
        this.monthlyRent = saveDto.getPrice();
        this.title = saveDto.getName();
    }

    public void updateSaleType() {
        this.saleType = SaleType.COMPLETE;
    }
}
