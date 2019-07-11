package com.hackathon.sharedeconomy.model.dtos.sign;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpdateRequest {
    private String pw;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer age;

    @ApiModelProperty(notes = "노인 또는 청년으로 보내야 한다.")
    private String role;
}
