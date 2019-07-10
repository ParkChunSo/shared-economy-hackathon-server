package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dtos.UserDto;
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
@RequestMapping(value = "/hanzipgachi/sign")
public class SIgnController {

    private SignService signService;

    public SIgnController(SignService signService) {
        this.signService = signService;
    }

    @ApiOperation(value = "로그인")
    @ApiImplicitParam(name = "loginDto", dataType = "LoginDto")
    @PostMapping("/signin")
    public User signIn(@RequestBody UserDto.Login loginDto){
        return signService.login(loginDto.toEntity());
    }


    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "signupDto", dataType = "SignupDto")
    @PostMapping("/signup")
    public User signUp(@RequestBody UserDto.SingUp signupDto){
        return signService.signup(signupDto.toEntity());
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
