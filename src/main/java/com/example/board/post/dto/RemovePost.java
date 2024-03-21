package com.example.board.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public class RemovePost {

  @Getter
  public static class Request {
    @NotNull
    private int postId;
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
    private LocalDateTime removedTime;
    private int userId;

    public static RemovePost.Response fromDto (PostDto postDto) {
      return Response.builder()
          .postId(postDto.getPostId())
          .title(postDto.getTitle())
          .content(postDto.getContent())
          .createdTime(postDto.getCreatedTime())
          .updatedTime(postDto.getUpdatedTime())
          .removedTime(postDto.getRemovedTime())
          .userId(postDto.getUserId())
          .build();
    }

  }

}
