package com.example.board.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UpdatePost {

  @Getter
  @Setter
  public static class Request {

    @NotNull
    private int postId;
    private String title;
    private String content;
    @NotNull
    private int userId;
  }

  @Builder
  @Getter
  public static class Response {

    private int postId;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;
    private int userId;

    public static UpdatePost.Response fromDto(PostDto postDto) {
      return UpdatePost.Response.builder()
          .postId(postDto.getPostId())
          .title(postDto.getTitle())
          .content(postDto.getContent())
          .createdTime(postDto.getCreatedTime())
          .updatedTime(postDto.getUpdatedTime())
          .deletedTime(postDto.getDeletedTime())
          .userId(postDto.getUserId())
          .build();
    }

  }

}
