package com.hackathon.sharedeconomy.model.dtos.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberListResponse {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer age;
}
