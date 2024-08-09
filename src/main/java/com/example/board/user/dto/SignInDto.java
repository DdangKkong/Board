package com.example.board.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SignInDto {

  @Getter
  @Setter
  public static class Request {

    @NotBlank
    private String userLoginId;
    @NotBlank
    private String password;

  }

  @Getter
  @Builder
  public static class Response {

    private int userId;

    private String token;

    private String userLoginId;

  }

}
