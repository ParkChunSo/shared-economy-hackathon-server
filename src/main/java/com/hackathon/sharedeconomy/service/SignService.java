package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class SignService {
    private LoginRepository loginRepository;

    public SignService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public User findById(String userId) {
        return loginRepository.findById(userId)
                .orElseThrow(() -> new UserDefineException("해당 Id의 유저가 없습니다."));
    }

    public User login(User connectUser) {
        User checkUser = loginRepository.findById(connectUser.getId()).orElseThrow(() -> new UserDefineException(connectUser.getId() + "의 아이디를 찾을 수 없습니다."));

        if (connectUser.getPw().equals(checkUser.getPw())) {
            return checkUser;
        } else {
            throw new UserDefineException(connectUser.getId() + "의 비밀번호가 잘못 되었습니다.");
        }
    }

    public User signup(User user) {
        if(!checkUser(user.getId())) {
            throw new UserDefineException("아이디 중복입니다.");
        }

        return loginRepository.save(user);
    }

    public Boolean checkUser(String userId) {
        return ObjectUtils.isEmpty(loginRepository.findById(userId));
    }


    /*public SignupDto update(SignupDto dtos) {
        User user = new User();
        if (loginRepository.findById(dtos.getId()).isPresent())
            user = loginRepository.save(dtos.toEntity());
        return SignupDto.builder()
                .id(user.getId())
                .pw(user.getPw())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getgetRole())
                .build();
    }*/
}
