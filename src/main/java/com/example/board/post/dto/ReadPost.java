package com.example.board.post.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public class ReadPost {

  @Builder
  @Getter // 제발 꼭 이거 붙이자...
  public static class Response {

    private int postId;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime removedTime;
    private int userId;

    public static ReadPost.Response fromDto (PostDto postDto) {
      return ReadPost.Response.builder()
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
