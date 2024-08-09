package com.example.board.post.controller;

import com.example.board.comment.service.CommentService;
import com.example.board.paging.PagingResponse;
import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.PostDto;
import com.example.board.post.dto.ReadPost;
import com.example.board.post.dto.DeletePost;
import com.example.board.post.dto.UpdatePost;
import com.example.board.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/board/posts")
//public class PostController {
//
//  private final PostService postService;
//
//  @PostMapping
//  public ResponseEntity<CreatePost.Response> createPost(@RequestBody @Valid CreatePost.Request request) {
//    PostDto postDto = postService.createPost(request);
//    return ResponseEntity.ok(CreatePost.Response.fromDto(postDto));
//  }
//
//  @GetMapping("/{post_id}")
//  public ResponseEntity<ReadPost.Response> readPost(@PathVariable(value = "post_id") int postId) {
//    PostDto postDto = postService.readPost(postId);
//    return ResponseEntity.ok(ReadPost.Response.fromDto(postDto));
//  }
//
//  @PutMapping
//  public ResponseEntity<UpdatePost.Response> updatePost(@RequestBody @Valid UpdatePost.Request request) {
//    PostDto postDto = postService.updatePost(request);
//    return ResponseEntity.ok(UpdatePost.Response.fromDto(postDto));
//  }
//
//  @DeleteMapping
//  public ResponseEntity<DeletePost.Response> deletePost (@RequestBody @Valid DeletePost.Request request) {
//    PostDto postDto = postService.deletePost(request);
//    return ResponseEntity.ok(DeletePost.Response.fromDto(postDto));
//  }
//
//}

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/posts")
public class PostController {

  private final PostService postService;
  private final CommentService commentService;

//  @PostMapping("/create")
//  public String createPostFormSubmit(@RequestBody @Valid CreatePost.Request request, Model model) {
//    PostDto postDto = postService.createPost(request);
//    model.addAttribute("post", postDto);
//    return "postDetail";
//  }

  @PostMapping("/create")
  public String createPostFormSubmit(@RequestBody @Valid CreatePost.Request request, HttpSession session, Model model) {

    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
      model.addAttribute("message", "Please login to create a post.");
      return "result";
    }
    request.setUserId(userId);
    PostDto postDto = postService.createPost(request);
    model.addAttribute("post", postDto);
    return "postDetail";
  }

//  @GetMapping("/{post_id}")
//  public String readPost(@PathVariable(value = "post_id") int postId, Model model) {
//    PostDto postDto = postService.readPost(postId);
//    model.addAttribute("post", postDto);
//    return "postDetail";
//  }

  @GetMapping("/{post_id}")
  public String readPost(@PathVariable(value = "post_id") int postId, Model model) {
    PostDto postDto = postService.readPost(postId);
    model.addAttribute("post", postDto);
    model.addAttribute("comments", commentService.getCommentsByPostId(postId));
    return "postDetail";
  }

//  @PutMapping("/update")
//  public String updatePostFormSubmit(@RequestBody @Valid UpdatePost.Request request, Model model) {
//    PostDto postDto = postService.updatePost(request);
//    model.addAttribute("message", "Post updated successfully!");
//    return "result";
//  }

  @PostMapping("/{post_id}/update")
  public String updatePostFormSubmit(@PathVariable(value = "post_id") Integer postId, @RequestBody @Valid UpdatePost.Request request, HttpSession session, Model model) {
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
      model.addAttribute("message", "Please log in to update the post.");
      return "result";
    }
    request.setUserId(userId);
    request.setPostId(postId);
    PostDto postDto = postService.updatePost(request);
    model.addAttribute("post", postDto);
    return "postDetail";
  }

//  @DeleteMapping("/delete")
//  public String deletePostFormSubmit(@RequestBody @Valid DeletePost.Request request, Model model) {
//    PostDto postDto = postService.deletePost(request);
//    model.addAttribute("message", "Post deleted successfully!");
//    return "result";
//  }

  @PostMapping("/{post_id}/delete")
  public String deletePostFormSubmit(@PathVariable(value = "post_id") Integer postId, HttpSession session, Model model) {
      Integer userId = (Integer) session.getAttribute("userId");
      if (userId == null) {
        model.addAttribute("message", "Please log in to delete the post.");
        return "result";
      }
      DeletePost.Request request = new DeletePost.Request();
      request.setUserId(userId);
      request.setPostId(postId);
      PostDto postDto = postService.deletePost(request);
      model.addAttribute("message", "Post deleted successfully!");
    return "result";
  }

  @GetMapping("/create")
//  @PreAuthorize("hasRole('USER')")
  public String createPostForm() {
    return "createPost";
  }

  @GetMapping("/{post_id}/update")
  public String updatePostForm(@PathVariable(value = "post_id") Long postId, Model model) {
    model.addAttribute("postId", postId);
    return "updatePost";
  }

//  @GetMapping("/{post_id}/delete")
//  public String deletePostForm(@PathVariable(value = "post_id") Long postId, Model model) {
//    model.addAttribute("postId", postId);
//    return "deletePost";
//  }

  // 프로젝트 모든 구인 글 조회 ( 메인 화면, page 요소 추가 )
  @GetMapping("/main")
  public String pagingPosts(
      @RequestParam(value = "page", required = false, defaultValue = "1") @Positive int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") @Positive int size,
      Model model) {

    PagingResponse pagingResponse = postService.pagingPosts(page-1, size);
    model.addAttribute("pagingResponse", pagingResponse);
    return "main";
  }
}
