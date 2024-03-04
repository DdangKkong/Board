package com.example.board.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private int id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "created_time")
  private LocalDateTime createdTime;

  @Column(name = "updated_time")
  private LocalDateTime updatedTime;

  @Column(name = "removed_time")
  private LocalDateTime removedTime;

}
