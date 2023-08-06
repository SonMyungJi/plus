package com.sparta.plus.service;

import com.sparta.plus.dto.LoginRequestDto;
import com.sparta.plus.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     * 유저 생성
     * @param requestDto 유저 생성 요청정보
     */
    void signup(SignupRequestDto requestDto);

    /**
     * 유저 로그인
     * @param requestDto 유저 생성 요청정보
     * @param response 응답정보
     * @return jwtCookie
     */
    void login(LoginRequestDto requestDto, HttpServletResponse response);
}
