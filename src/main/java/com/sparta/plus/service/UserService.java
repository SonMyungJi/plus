package com.sparta.plus.service;

import com.sparta.plus.dto.LoginRequestDto;
import com.sparta.plus.dto.SignupRequestDto;
import com.sparta.plus.entity.User;
import com.sparta.plus.jwt.JwtUtil;
import com.sparta.plus.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(nickname, password);
        userRepository.save(user);
    }

    @Transactional
    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();

        User user = userRepository.findByNickname(nickname).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createToken(user.getNickname());

        Cookie jwtCookie = new Cookie("jwt", accessToken);
        jwtCookie.setMaxAge(1800);
        jwtCookie.setPath("/");

        response.addCookie(jwtCookie);
    }
}
