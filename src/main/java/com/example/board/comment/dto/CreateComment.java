package com.example.board.comment.dto;

import com.example.board.comment.domain.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CreateComment {

  @Getter
  @Setter
  public static class Request {

    @NotBlank
    private String content;
    @NotNull
    private int postId;
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

    public static CreateComment.Response fromDto(CommentDto commentDto) {
      return CreateComment.Response.builder()
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
