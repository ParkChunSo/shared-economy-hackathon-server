package com.hackathon.sharedeconomy.model.dtos.sign;

import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    private String id;
    private String pw;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer age;

    @ApiModelProperty(notes = "노인 또는 청년으로 보내야 한다.")
    private String role;

    public User toEntity() {
        return User.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .phoneNumber(phoneNumber)
                .address(address)
                .age(age)
                .role(RoleType.convertRoleType(role))
                .build();
    }
}
