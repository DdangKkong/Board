package com.example.board.post.service;

import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.post.domain.Post;
import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.CreatePost.Request;
import com.example.board.post.dto.CreatePost.Response;
import com.example.board.post.dto.PostDto;
import com.example.board.post.repository.PostRepository;
import com.example.board.user.domain.User;
import com.example.board.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public PostDto createPost(CreatePost.Request request) {
    User user = getUser(request.getUserId());

    Post post = Post.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .createdTime(LocalDateTime.now())
        .user(user)
        .build();
    postRepository.save(post);

    return PostDto.fromEntity(post);
  }

  private User getUser(int userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USERID_INVALID));
  }
}
