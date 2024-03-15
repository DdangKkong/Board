package com.example.board.comment.service;

import com.example.board.comment.domain.Comment;
import com.example.board.comment.dto.CommentDto;
import com.example.board.comment.dto.CreateComment;
import com.example.board.comment.dto.CreateComment.Request;
import com.example.board.comment.dto.ReadComment;
import com.example.board.comment.dto.RemoveComment;
import com.example.board.comment.dto.UpdateComment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.post.domain.Post;
import com.example.board.post.repository.PostRepository;
import com.example.board.user.domain.User;
import com.example.board.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  private User getUser(int userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USERID_INVALID));
  }
  private Post getPost(int postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new AppException(ErrorCode.POSTID_INVALID));
  }
  private Comment getComment(int commentId) {
    return commentRepository.findById(commentId)
        .orElseThrow(() -> new AppException(ErrorCode.COMMENTID_INVALID));
  }

  public CommentDto createComment(Request request) {
    User user = getUser(request.getUserId());
    Post post = getPost(request.getPostId());

    Comment comment = Comment.builder()
        .content(request.getContent())
        .createdTime(LocalDateTime.now())
        .post(post)
        .user(user)
        .build();
    commentRepository.save(comment);

    return CommentDto.fromEntity(comment);
  }

  public CommentDto readComment(int commentId) {
    Comment comment = getComment(commentId);

    return CommentDto.fromEntity(comment);
  }

  @Transactional
  public CommentDto updateComment(UpdateComment.Request request) {
    User user = getUser(request.getUserId());
    Post post = getPost(request.getPostId());
    Comment comment = getComment(request.getCommentId());

    // 댓글 작성자인지 확인
    if (comment.getUser().getId() != request.getUserId()) {
      new AppException(ErrorCode.USERID_UNMATCHED);
    }
    // 댓글이 달린 게시글 정보인지 확인
    if (comment.getPost().getId() != request.getPostId()) {
      new AppException(ErrorCode.POSTID_UNMATCHED);
    }

    comment.setContent(request.getContent());
    comment.setUpdatedTime(LocalDateTime.now());

    return CommentDto.fromEntity(comment);
  }

  @Transactional
  public CommentDto removeComment(RemoveComment.Request request) {
    User user = getUser(request.getUserId());
    Post post = getPost(request.getPostId());
    Comment comment = getComment(request.getCommentId());

    // 댓글 작성자인지 확인
    if (comment.getUser().getId() != request.getUserId()) {
      new AppException(ErrorCode.USERID_UNMATCHED);
    }
    // 댓글이 달린 게시글 정보인지 확인
    if (comment.getPost().getId() != request.getPostId()) {
      new AppException(ErrorCode.POSTID_UNMATCHED);
    }

    // 프론트에서 보이지 않게 설정, 데이터는 살려놓음
    comment.setRemovedTime(LocalDateTime.now());

    return CommentDto.fromEntity(comment);
  }
}
