package com.example.board.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class SignInDto {

  @Getter
  public static class Request {

    @NotNull
    private String userLoginId;
    @NotNull
    private String password;

  }

  @Getter
  @Builder
  public static class Response {

    private String token;

  }

}
