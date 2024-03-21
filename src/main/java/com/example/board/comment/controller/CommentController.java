package com.example.board.comment.controller;

import com.example.board.comment.dto.CommentDto;
import com.example.board.comment.dto.CreateComment;
import com.example.board.comment.dto.ReadComment;
import com.example.board.comment.dto.RemoveComment;
import com.example.board.comment.dto.UpdateComment;
import com.example.board.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<CreateComment.Response> createComment(@RequestBody @Valid CreateComment.Request request) {
    CommentDto commentDto = commentService.createComment(request);

    return ResponseEntity.ok(CreateComment.Response.fromDto(commentDto));
  }

  @GetMapping("{comment_id}")
  public ResponseEntity<ReadComment.Response> readComment(
      @PathVariable(name = "comment_id") int commentId) {
    CommentDto commentDto = commentService.readComment(commentId);

    return ResponseEntity.ok(ReadComment.Response.fromDto(commentDto));
  }

  @PutMapping
  public ResponseEntity<UpdateComment.Response> updateComment(@RequestBody @Valid UpdateComment.Request request) {
    CommentDto commentDto = commentService.updateComment(request);

    return ResponseEntity.ok(UpdateComment.Response.fromDto(commentDto));
  }

  @DeleteMapping
  public ResponseEntity<RemoveComment.Response> removeComment(
      @RequestBody @Valid RemoveComment.Request request) {
    CommentDto commentDto = commentService.removeComment(request);

    return ResponseEntity.ok(RemoveComment.Response.fromDto(commentDto));
  }

}
