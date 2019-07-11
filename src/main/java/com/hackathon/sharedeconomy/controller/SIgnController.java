package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dtos.sign.*;
import com.hackathon.sharedeconomy.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String signIn(@RequestBody SignInRequest requestDto){
        return signService.signIn(requestDto);
    }


    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "SignUpRequest", dataType = "SignUpRequest")
    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest requestDto){
        signService.signup(requestDto);
    }

    @ApiOperation(value = "회원정보 상세조회(일반 회원)")
    @GetMapping("/check")
    public UserDetailResponse getUserInfo(@RequestHeader(name = "Authorization") String jwt){
        return signService.getUserInfo(jwt);
    }
    @ApiOperation(value = "회원정보 수정(일반 회원)")
    @ApiImplicitParam(name = "SignUpdateRequest", dataType = "SignUpdateRequest")
    @PostMapping("/update")
    public void updateInfo(@RequestHeader(name = "Authorization") String jwt, @RequestBody SignUpdateRequest requestDto){
        signService.updateInfo(jwt, requestDto);
    }

    @ApiOperation(value = "회원정보 상세조회(관리자)")
    @GetMapping("/admin/{id}")
    public UserDetailResponse getUserInfoForAdmin(@PathVariable String id){
        return signService.getUserInfoForAdmin(id);
    }

    @ApiOperation(value = "회원정보 리스트조회(관리자)")
    @GetMapping("/admin/list")
    public List<UserListResponse> getUserInfoList(){
        return signService.getUserInfoList();
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
