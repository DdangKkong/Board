package com.example.board.comment.dto;

import com.example.board.comment.domain.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentDto {

  private int commentId;

  private String content;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;

  private LocalDateTime removedTime;

  private int postId;

  private int userId;

  public static CommentDto fromEntity(Comment comment){
    return CommentDto.builder()
        .commentId(comment.getId())
        .content(comment.getContent())
        .createdTime(comment.getCreatedTime())
        .updatedTime(comment.getUpdatedTime())
        .removedTime(comment.getRemovedTime())
        .postId(comment.getPost().getId())
        .userId(comment.getUser().getId())
        .build();
  }

}
