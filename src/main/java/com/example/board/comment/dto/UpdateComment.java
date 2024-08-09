package com.example.board.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UpdateComment {

  @Getter
  @Setter
  public static class Request {

    @NotNull
    private int commentId;
    @NotBlank
    private String content;
    @NotNull
    private int postId;
    @NotNull
    private int userId;

  }

  @Builder
  @Getter
  public static class Response {

    private int commentId;

    private String content;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime deletedTime;

    private int postId;

    private int userId;

    public static UpdateComment.Response fromDto(CommentDto commentDto) {
      return UpdateComment.Response.builder()
          .commentId(commentDto.getCommentId())
          .content(commentDto.getContent())
          .createdTime(commentDto.getCreatedTime())
          .updatedTime(commentDto.getUpdatedTime())
          .deletedTime(commentDto.getDeletedTime())
          .postId(commentDto.getPostId())
          .userId(commentDto.getUserId())
          .build();
    }
  }

}
