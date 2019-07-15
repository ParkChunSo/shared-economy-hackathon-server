package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String id;

    private String pw;

    private String name;

    private String phoneNumber;

    // 사용자의 실거주지 주소
    private String residence;

    //희망 거주 지역 주소
    private String city;

    private String gu;

    private String dong;

    private Integer age;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<ForSale> forSales = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Shopping> shoppings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public User(String id, String pw, String name, String phoneNumber, String residence, String city, String gu, String dong, Integer age, List<ForSale> forSales, List<Shopping> shoppings, RoleType role) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.age = age;
        this.forSales = forSales;
        this.shoppings = shoppings;
        this.role = role;
    }

    public User updateUserInfo(String pw, String name, String phoneNumber, String residence, int age, RoleType role){
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.age = age;
        this.role = role;

        return this;
    }
}
