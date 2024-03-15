package com.example.board.user.dto;

import lombok.Builder;
import lombok.Getter;

public class SignInDto {

  @Getter
  public static class Request {

    private String userLoginId;
    private String password;

  }

  @Getter
  @Builder
  public static class Response {

    private String token;

  }

}
