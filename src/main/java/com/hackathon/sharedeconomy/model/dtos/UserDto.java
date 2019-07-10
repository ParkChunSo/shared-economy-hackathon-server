package com.hackathon.sharedeconomy.model.dtos;

import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-03-05.
 */

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Login {
        private String id;
        private String pw;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .pw(pw)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SingUp {
        private String id;
        private String pw;
        private String name;
        private String phoneNumber;
        private String address;
        private Integer age;
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
}
