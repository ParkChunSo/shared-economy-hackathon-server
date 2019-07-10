package com.hackathon.sharedeconomy.model.dtos;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorDto {

    private String originalErrorMessage;
    private String errorMessage;
    private String requestURL;

    @Builder
    public ErrorDto(String originalErrorMessage, String errorMessage, String requestURL) {
        this.originalErrorMessage = originalErrorMessage;
        this.errorMessage = errorMessage;
        this.requestURL = requestURL;
    }
}
