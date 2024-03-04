package com.example.board.user.service;

import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.security.JwtProvider;
import com.example.board.user.domain.User;
import com.example.board.user.dto.SignInDto;
import com.example.board.user.dto.SignUpDto;
import com.example.board.user.dto.SignUpDto.Request;
import com.example.board.user.dto.SignUpDto.Response;
import com.example.board.user.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${spring.jwt.secret}")
  private String secretKey;

  public Response signUp(Request request) {

    // 아이디가 중복되는지 확인
    if (userRepository.existsByUserLoginId(request.getUserLoginId())) {
      throw new AppException(ErrorCode.USERLOGINID_DUPLICATED);
    }

    // 닉네임이 중복되는지 확인
    if (userRepository.existsByNickname(request.getNickname())) {
      throw new AppException(ErrorCode.NICKNAME_DUPLICATED);
    }

    // 사용자가 적은 비밀번호 두개가 일치하는지 확인
    if (!Objects.equals(request.getPassword(), request.getPasswordCheck())) {
      throw new AppException(ErrorCode.PASSWORD_NOT_EQUALS);
    }

    request.setPassword(passwordEncoder.encode(request.getPassword()));

    User user = SignUpDto.Request.toEntity(request);
    userRepository.save(user);

    return Response.fromEntity(user);

  }

  public SignInDto.Response signIn(SignInDto.Request request) {

    // 일치하는 아이디가 있는지 확인 ( userLoginId )
    if (!userRepository.existsByUserLoginId(request.getUserLoginId())) {
      throw new AppException(ErrorCode.USERLOGINID_NOTFOUND);
    }
    User user = userRepository.findByUserLoginId(request.getUserLoginId());

    // 비밀번호 일치 확인
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new AppException(ErrorCode.PASSWORD_INVALID);
    }

    String token = JwtProvider.generateToken(user.getUserLoginId(), secretKey);

    return SignInDto.Response.builder().token(token).build();
  }


}
