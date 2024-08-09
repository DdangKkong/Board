package com.example.board.user.controller;

import com.example.board.user.dto.SignInDto;
import com.example.board.user.dto.SignUpDto;
import com.example.board.user.dto.SignUpDto.Request;
import com.example.board.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor // final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
//@RequestMapping("/users")
//public class UserController {
//
//  private final UserService userService;
//
//  @PostMapping("/signup")
//  public ResponseEntity<SignUpDto.Response> signUp (@RequestBody @Valid SignUpDto.Request request) {
//    SignUpDto.Response response = userService.signUp(request);
//    return ResponseEntity.ok(response);
//  }
//
//  @PostMapping("/signin")
//  public ResponseEntity<SignInDto.Response> signIn (@RequestBody @Valid SignInDto.Request request) {
//    SignInDto.Response response = userService.signIn(request);
//    return ResponseEntity.ok(response);
//  }
//
//}

@Controller
@RequiredArgsConstructor // final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

//  @PostMapping("/signup")
//  public ResponseEntity<SignUpDto.Response> signUp(@RequestBody @Valid SignUpDto.Request request) {
//    SignUpDto.Response response = userService.signUp(request);
//    return ResponseEntity.ok(response);
//  }
//
//  @PostMapping("/signin")
//  public ResponseEntity<SignInDto.Response> signIn(@RequestBody @Valid SignInDto.Request request) {
//    SignInDto.Response response = userService.signIn(request);
//    return ResponseEntity.ok(response);
//  }

  @GetMapping("/signup")
  public String signUpForm() {
    return "signup";
  }

  @PostMapping("/signup/form")
  public String signUpFormSubmit(
      @RequestParam(name = "userLoginId") String userLoginId,
      @RequestParam(name = "password") String password,
      @RequestParam(name = "passwordCheck") String passwordCheck,
      @RequestParam(name = "nickname") String nickname,
      Model model
  ) {
    SignUpDto.Request request = new Request();
    request.setUserLoginId(userLoginId);
    request.setPassword(password);
    request.setPasswordCheck(passwordCheck);
    request.setNickname(nickname);

    SignUpDto.Response response = userService.signUp(request);
    model.addAttribute("message", "Sign-Up successful! WELCOME " + response.getNickname());
    return "result";
  }

  @GetMapping("/signin")
  public String signInForm() {
    return "signin";
  }

  @PostMapping("/signin/form")
  public String signInFormSubmit(@RequestParam String userLoginId, @RequestParam String password, HttpSession session, Model model) {
    SignInDto.Request request = new SignInDto.Request();
    request.setUserLoginId(userLoginId);
    request.setPassword(password);
    SignInDto.Response response = userService.signIn(request);
    if (response != null) {
      session.setAttribute("userId", response.getUserId());
      model.addAttribute("message", "Sign-In successful! Welcome " + response.getUserLoginId());
    } else {
      model.addAttribute("message", "Sign-In failed!");
    }
    return "result";
  }

//  @PostMapping("/signin/form")
//  public String signInFormSubmit(
//      @RequestParam(name = "userLoginId") String userLoginId,
//      @RequestParam(name = "password") String password,
//      Model model
//  ) {
//    SignInDto.Request request = new SignInDto.Request();
//    request.setUserLoginId(userLoginId);
//    request.setPassword(password);
//
//    SignInDto.Response response = userService.signIn(request);
//    model.addAttribute("message", "Sign-In successful! Welcome " + response.getUserLoginId());
//    return "result";
//  }
}
