package com.example.board.user.dto;

import com.example.board.user.domain.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SignUpDto {

  @Getter
  @Setter
  public static class Request{

    @NotNull
    private String userLoginId;
    @NotNull
    private String password;
    @NotNull
    private String passwordCheck;
    @NotNull
    private String nickname;

    public static User toEntity(SignUpDto.Request request) { // static 이 있어야 service 에서 사용가능
      return User.builder()
          .userLoginId(request.userLoginId)
          .password(request.password)
          .nickname(request.nickname)
          .createdTime(LocalDateTime.now())
          .build();
    }

  }

  @Builder
  @Getter
  public static class Response{

    private int id;

    private String userLoginId;

    private String nickname;

    private LocalDateTime createdTime;

    public static SignUpDto.Response fromEntity(User user){
      return Response.builder()
          .id(user.getId())
          .userLoginId(user.getUserLoginId())
          .nickname(user.getNickname())
          .createdTime(user.getCreatedTime())
          .build();
    }

  }

}
