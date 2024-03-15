package com.example.board.post.dto;

import com.example.board.post.domain.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostDto {

  private int postId;

  private String title;

  private String content;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;

  private LocalDateTime removedTime;

  private int userId;

  public static PostDto fromEntity(Post post) {
    return PostDto.builder()
        .postId(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .createdTime(post.getCreatedTime())
        .updatedTime(post.getUpdatedTime())
        .removedTime(post.getRemovedTime())
        .userId(post.getUser().getId())
        .build();
  }

}
