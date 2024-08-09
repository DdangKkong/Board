package com.example.board.comment.controller;

import com.example.board.comment.dto.CommentDto;
import com.example.board.comment.dto.CreateComment;
import com.example.board.comment.dto.CreateComment.Request;
import com.example.board.comment.dto.ReadComment;
import com.example.board.comment.dto.DeleteComment;
import com.example.board.comment.dto.UpdateComment;
import com.example.board.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comments")
public class CommentController {

  private final CommentService commentService;
  private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


  @PostMapping
  public ResponseEntity<CreateComment.Response> createComment(
      @RequestBody @Valid CreateComment.Request request,
      HttpSession session) {

    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    request.setUserId(userId);
    CommentDto commentDto = commentService.createComment(request);
    return ResponseEntity.ok(CreateComment.Response.fromDto(commentDto));
  }
//
//  @GetMapping("{comment_id}")
//  public ResponseEntity<ReadComment.Response> readComment(
//      @PathVariable(name = "comment_id") int commentId) {
//    CommentDto commentDto = commentService.readComment(commentId);
//
//    return ResponseEntity.ok(ReadComment.Response.fromDto(commentDto));
//  }
//
//  @PutMapping
//  public ResponseEntity<UpdateComment.Response> updateComment(@RequestBody @Valid UpdateComment.Request request) {
//    CommentDto commentDto = commentService.updateComment(request);
//
//    return ResponseEntity.ok(UpdateComment.Response.fromDto(commentDto));
//  }
//
//  @DeleteMapping
//  public ResponseEntity<DeleteComment.Response> deleteComment(
//      @RequestBody @Valid DeleteComment.Request request) {
//    CommentDto commentDto = commentService.deleteComment(request);
//
//    return ResponseEntity.ok(DeleteComment.Response.fromDto(commentDto));
//  }

//  @PostMapping
//  public String createComment(
//      @RequestBody @Valid CreateComment.Request request,
////      @PathVariable(value = "post_id") int postId,
////      @PathVariable(value = "content") String content,
//      HttpSession session, Model model) {
//
//    Integer userId = (Integer) session.getAttribute("userId");
//    if (userId == null) {
//      model.addAttribute("message", "Please login to create a comment.");
//      return "result";
//    }
//
////    CreateComment.Request request = new Request();
//    request.setUserId(userId);
////    request.setPostId(postId);
////    request.setContent(content);
//
//    CommentDto commentDto = commentService.createComment(request);
//    model.addAttribute("comment", commentDto);
//    return "postDetail";
//  }

  @GetMapping("{comment_id}")
  public ResponseEntity<ReadComment.Response> readComment(
      @PathVariable(name = "comment_id") int commentId) {
    CommentDto commentDto = commentService.readComment(commentId);
    return ResponseEntity.ok(ReadComment.Response.fromDto(commentDto));
  }

  @PutMapping("/{comment_id}")
  public ResponseEntity<?> updateComment(
      @PathVariable int comment_id,
      @RequestBody @Valid UpdateComment.Request request,
      HttpSession session) {

    logger.info("Received update request for comment id: {}", comment_id);
    logger.info("Request body: {}", request);

    Integer userId = (Integer) session.getAttribute("userId");
    logger.info("User ID from session: {}", userId);

    if (userId == null) {
      logger.warn("User not authenticated");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

    request.setCommentId(comment_id);
    request.setUserId(userId);

    try {
      logger.info("Calling commentService.updateComment with request: {}", request);
      CommentDto commentDto = commentService.updateComment(request);
      logger.info("Comment updated successfully: {}", commentDto);
      return ResponseEntity.ok(UpdateComment.Response.fromDto(commentDto));
    } catch (Exception e) {
      logger.error("Failed to update comment", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update comment: " + e.getMessage());
    }
  }

  @DeleteMapping("/{comment_id}")
  public ResponseEntity<?> deleteComment(
      @PathVariable int comment_id,
      @RequestBody @Valid DeleteComment.Request request,
      HttpSession session
  ) {
    Integer userId = (Integer) session.getAttribute("userId");
    logger.info("User ID from session: {}", userId);

    if (userId == null) {
      logger.warn("User not authenticated");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

    request.setCommentId(comment_id);
    request.setUserId(userId);

    try {
      logger.info("Calling commentService.updateComment with request: {}", request);
      CommentDto commentDto = commentService.deleteComment(request);
      logger.info("Comment delete successfully: {}", commentDto);
      return ResponseEntity.ok(DeleteComment.Response.fromDto(commentDto));
    } catch (Exception e) {
      logger.error("Failed to delete comment", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete comment: " + e.getMessage());
    }

  }

}
