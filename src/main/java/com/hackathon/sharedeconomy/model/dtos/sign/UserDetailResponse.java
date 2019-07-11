package com.hackathon.sharedeconomy.model.dtos.sign;

import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailResponse {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer age;
    private RoleType role;

    @Builder
    public UserDetailResponse(String id, String name, String phoneNumber, String address, Integer age, RoleType role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.role = role;
    }
}
