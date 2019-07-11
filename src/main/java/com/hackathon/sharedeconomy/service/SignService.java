package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dtos.sign.SignInRequest;
import com.hackathon.sharedeconomy.model.dtos.sign.SignUpRequest;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.LoginRepository;
import com.hackathon.sharedeconomy.utill.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class SignService {
    private final LoginRepository loginRepository;

    public SignService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String signIn(SignInRequest request) {
        User user = findById(request.getId());

        if (request.getPw().equals(user.getPw())) {
            return JwtProvider.createToken(user.getId(), user.getRole());
        } else {
            throw new UserDefineException(request.getId() + "의 비밀번호가 잘못 되었습니다.");
        }
    }

    public void signup(SignUpRequest request) {
        if(IsAlreadyUsed(request.getId())) {
            throw new UserDefineException("아이디 중복입니다.");
        }

        loginRepository.save(request.toEntity());
    }

//    public SignupDto update(SignupDto dtos) {
//        User user = new User();
//        if (loginRepository.findById(dtos.getId()).isPresent())
//            user = loginRepository.save(dtos.toEntity());
//        return SignupDto.builder()
//                .id(user.getId())
//                .pw(user.getPw())
//                .name(user.getName())
//                .phoneNumber(user.getPhoneNumber())
//                .role(user.getgetRole())
//                .build();
//    }

    public User findById(String userId) {
        return loginRepository.findById(userId)
                .orElseThrow(() -> new UserDefineException("해당 아이디를 찾을 수 없습니다."));
    }

    public Boolean IsAlreadyUsed(String userId) {
        return !ObjectUtils.isEmpty(loginRepository.findById(userId));
    }

}
