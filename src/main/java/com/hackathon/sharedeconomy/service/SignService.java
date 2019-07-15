package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dtos.sign.*;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import com.hackathon.sharedeconomy.repository.LoginRepository;
import com.hackathon.sharedeconomy.utill.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignService {
    private final LoginRepository loginRepository;

    public SignService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // 로그인
    public String signIn(SignInRequest request) {
        User user = findById(request.getId());

        if (request.getPw().equals(user.getPw())) {
            return JwtUtils.createToken(user.getId(), user.getRole());
        } else {
            throw new UserDefineException(request.getId() + "의 비밀번호가 잘못 되었습니다.");
        }
    }

    // 회원가입
    public void signUp(SignUpRequest request) {
        if(IsAlreadyUsed(request.getId())) {
            throw new UserDefineException("아이디 중복입니다.");
        }

        loginRepository.save(request.toEntity());
    }

    // 회원정보 수정
    public void updateInfo(String jwt, SignUpdateRequest requestdto) {
        User user = findById(
                JwtUtils.getUserIdByToken(
                        JwtUtils.resolveToken(jwt)
                )
        );

        loginRepository.save(
                user.updateUserInfo(
                        requestdto.getPw(),
                        requestdto.getName(),
                        requestdto.getPhoneNumber(),
                        requestdto.getAddress(),
                        requestdto.getAge(),
                        RoleType.convertRoleType(requestdto.getRole()))
        );
    }

    // Jwt를 이용한 회원정보 조회
    public UserDetailResponse getUserInfo(String jwt) {
        User user = findById(
                JwtUtils.getUserIdByToken(
                        JwtUtils.resolveToken(jwt)   // Bearer 뒤에 토큰 받기
                )
        );
        return UserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getResidence())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    // (관리자) 회원정보 조회
    public UserDetailResponse getUserInfoForAdmin(String id){
        User user = findById(id);
        return UserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getResidence())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    // (관리자) 회원정보 리스트 조회
    public List<UserListResponse> getUserInfoList() {
        List<UserListResponse> dtoList = new ArrayList<>();
        List<User> userList = loginRepository.findAll();

        for(User user : userList){
            dtoList.add(
                    UserListResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .address(user.getResidence())
                            .age(user.getAge())
                            .role(user.getRole().name())
                            .build()
            );
        }
        return dtoList;
    }

    public User findById(String userId) {
        return loginRepository.findById(userId)
                .orElseThrow(() -> new UserDefineException("해당 아이디를 찾을 수 없습니다."));
    }

    private Boolean IsAlreadyUsed(String userId) {
        return !ObjectUtils.isEmpty(loginRepository.findById(userId));
    }
}
