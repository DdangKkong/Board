package com.example.board.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class DeleteComment {

  @Getter
  @Setter
  public static class Request {

    @NotNull
    private int commentId;
    @NotNull
    private int userId;
    @NotNull
    private int postId;

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

    public static DeleteComment.Response fromDto(CommentDto commentDto) {
      return DeleteComment.Response.builder()
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
