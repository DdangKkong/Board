package com.example.board.comment.service;

import com.example.board.comment.domain.Comment;
import com.example.board.comment.dto.CommentDto;
import com.example.board.comment.dto.CreateComment;
import com.example.board.comment.dto.CreateComment.Request;
import com.example.board.comment.dto.ReadComment;
import com.example.board.comment.dto.DeleteComment;
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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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
  public CommentDto deleteComment(DeleteComment.Request request) {
    try {
      User user = getUser(request.getUserId());
      Post post = getPost(request.getPostId());
      Comment comment = getComment(request.getCommentId());

      if (comment == null) {
        throw new AppException(ErrorCode.COMMENTID_INVALID);
      }

      // 댓글 작성자인지 확인
      if (comment.getUser() == null || comment.getUser().getId() != request.getUserId()) {
        throw new AppException(ErrorCode.USERID_UNMATCHED);
      }
      // 댓글이 달린 게시글 정보인지 확인
      if (comment.getPost() == null || comment.getPost().getId() != request.getPostId()) {
        throw new AppException(ErrorCode.POSTID_UNMATCHED);
      }

      // 프론트에서 보이지 않게 설정, 데이터는 살려놓음
      comment.setDeletedTime(LocalDateTime.now());

      commentRepository.save(comment);  // 변경 사항을 명시적으로 저장

      return CommentDto.fromEntity(comment);
    } catch (AppException e) {
      // 애플리케이션 정의 예외 로깅 및 재던지기
      log.error("Application error in deleteComment: {}", e.getMessage());
      throw e;
    } catch (Exception e) {
      // 예상치 못한 예외 처리
      log.error("Unexpected error in deleteComment", e);
      throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  public List<CommentDto> getCommentsByPostId(int postId) {
    return commentRepository.findAllByPostId(postId).stream()
        .map(CommentDto::fromEntity)
        .collect(Collectors.toList());
  }
}
