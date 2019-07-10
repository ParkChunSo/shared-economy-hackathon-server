package com.hackathon.sharedeconomy.model.dtos.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequest {
    private String id;
    private String pw;
}
