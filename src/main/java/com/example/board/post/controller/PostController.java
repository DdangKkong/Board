package com.example.board.post.controller;

import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.PostDto;
import com.example.board.post.dto.ReadPost;
import com.example.board.post.dto.RemovePost;
import com.example.board.post.dto.UpdatePost;
import com.example.board.post.service.PostService;
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
@RequestMapping("/board/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CreatePost.Response> createPost(@RequestBody @Valid CreatePost.Request request) {
    PostDto postDto = postService.createPost(request);
    return ResponseEntity.ok(CreatePost.Response.fromDto(postDto));
  }

  @GetMapping("/{post_id}")
  public ResponseEntity<ReadPost.Response> readPost(@PathVariable(value = "post_id") int postId) {
    PostDto postDto = postService.readPost(postId);
    return ResponseEntity.ok(ReadPost.Response.fromDto(postDto));
  }

  @PutMapping
  public ResponseEntity<UpdatePost.Response> updatePost(@RequestBody @Valid UpdatePost.Request request) {
    PostDto postDto = postService.updatePost(request);
    return ResponseEntity.ok(UpdatePost.Response.fromDto(postDto));
  }

  @DeleteMapping
  public ResponseEntity<RemovePost.Response> removePost (@RequestBody @Valid RemovePost.Request request) {
    PostDto postDto = postService.removePost(request);
    return ResponseEntity.ok(RemovePost.Response.fromDto(postDto));
  }

}
