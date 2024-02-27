package com.example.board.user.controller;

import com.example.board.user.dto.SignUpDto;
import com.example.board.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<SignUpDto.Response> signUp (@RequestBody @Valid SignUpDto.Request request) {
    SignUpDto.Response response = userService.signup(request);
    return ResponseEntity.ok(response);
  }

}
