package com.example.board.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CreatePost {

  @Getter
  @Setter
  @Builder
  public static class Request {

    @NotNull
    private int userId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

  }


  @Builder
  @Getter
  @Setter
  public static class Response {

    private int postId;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private int userId;

    public static CreatePost.Response fromDto(PostDto postDto) {
      return Response.builder()
          .postId(postDto.getPostId())
          .title(postDto.getTitle())
          .content(postDto.getContent())
          .createdTime(postDto.getCreatedTime())
          .userId(postDto.getUserId())
          .build();
    }
  }

}
