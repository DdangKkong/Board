package com.example.board.paging;

import com.example.board.post.domain.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponseDto {

  private int postId;

  private int userId;

  private String title;

  private String content;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;

  private LocalDateTime deletedTime;

  @Builder
  @Getter
  public static class Response {

    private int postId;

    private int userId;

    private String title;

    private String content;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime deletedTime;

    public static PagingResponseDto.Response fromEntity (Post post){
      return Response.builder()
          .postId(post.getId())
          .userId(post.getUser().getId())
          .title(post.getTitle())
          .content(post.getContent())
          .createdTime(post.getCreatedTime())
          .updatedTime(post.getUpdatedTime())
          .deletedTime(post.getDeletedTime())
          .build();
    }
  }
}
