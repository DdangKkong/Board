package com.example.board.post.controller;

import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.PostDto;
import com.example.board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CreatePost.Response> createPost(@RequestBody @Valid CreatePost.Request request) {
    PostDto postDto = postService.createPost(request);
    return ResponseEntity.ok(CreatePost.Response.fromDto(postDto));
  }

}
