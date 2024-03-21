package com.example.board.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class SignInDto {

  @Getter
  public static class Request {

    @NotBlank
    private String userLoginId;
    @NotBlank
    private String password;

  }

  @Getter
  @Builder
  public static class Response {

    private String token;

  }

}
