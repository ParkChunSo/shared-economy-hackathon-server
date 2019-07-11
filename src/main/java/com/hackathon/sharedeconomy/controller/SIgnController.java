package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dtos.sign.SignInRequest;
import com.hackathon.sharedeconomy.model.dtos.sign.SignUpRequest;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "회원관련 API")
@RestController
@RequestMapping(value = "/sign")
public class SIgnController {

    private final SignService signService;

    public SIgnController(SignService signService) {
        this.signService = signService;
    }

    @ApiOperation(value = "로그인")
    @ApiImplicitParam(name = "SignInRequest", dataType = "SignInRequest")
    @PostMapping("/signin")
    public String signIn(@RequestBody SignInRequest signInDto){
        return signService.signIn(signInDto);
    }


    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "SignUpRequest", dataType = "SignUpRequest")
    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signupDto){
        signService.signup(signupDto);
    }


    /*@ApiOperation(value = "회원정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signupDto", dataType = "SignupDto"),
    })
    @PostMapping("/update")
    public SignupDto update(@RequestBody SignupDto signupDto){
        return loginService.update(signupDto);
    }*/
}
