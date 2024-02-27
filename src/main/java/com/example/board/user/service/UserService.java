package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.SignUpDto;
import com.example.board.user.dto.SignUpDto.Request;
import com.example.board.user.dto.SignUpDto.Response;
import com.example.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Response signup(Request request) {

    // 로그인 아이디가 중복되는지 확인
    if (userRepository.existsByUserLoginId(request.getUserLoginId())) {
      throw new RuntimeException("Login ID already exists");
    }

    // 닉네임이 중복되는지 확인
    if (userRepository.existsByNickname(request.getNickname())) {
      throw new RuntimeException("nickname is already exists");
    }

    request.setPassword(passwordEncoder.encode(request.getPassword()));

    User user = SignUpDto.Request.toEntity(request);
    userRepository.save(user);

    return Response.fromEntity(user);

  }

}
