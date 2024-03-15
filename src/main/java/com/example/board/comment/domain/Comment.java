package com.example.board.comment.domain;

import com.example.board.post.domain.Post;
import com.example.board.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private int id;

  @Column(name = "content")
  private String content;

  @Column(name = "created_time")
  private LocalDateTime createdTime;

  @Column(name = "updated_date")
  private LocalDateTime updatedTime;

  @Column(name = "removed_time")
  private LocalDateTime removedTime;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
