package com.hackathon.sharedeconomy.model.dtos.sign;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResponse {
    private String id;
    private String name;
    private String address;
    private Integer age;
    private String role;

    @Builder
    public UserListResponse(String id, String name, String address, Integer age, String role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.role = role;
    }
}
