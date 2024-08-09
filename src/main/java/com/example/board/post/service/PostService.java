package com.example.board.post.service;

import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.paging.PagingResponse;
import com.example.board.paging.PagingResponseDto;
import com.example.board.post.domain.Post;
import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.CreatePost.Request;
import com.example.board.post.dto.CreatePost.Response;
import com.example.board.post.dto.PostDto;
import com.example.board.post.dto.DeletePost;
import com.example.board.post.dto.UpdatePost;
import com.example.board.post.repository.PostRepository;
import com.example.board.user.domain.User;
import com.example.board.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  public PostDto readPost(int postId) {
    Post post = getPost(postId);
    return PostDto.fromEntity(post);
  }

  @Transactional
  public PostDto updatePost(UpdatePost.Request request) {
    User user = getUser(request.getUserId());
    Post post = getPost(request.getPostId());

    // 클라이언트의 user 정보가 게시글 작성자와 일치여부 확인
    if (post.getUser().getId() != request.getUserId()) {
      throw new AppException(ErrorCode.USERID_UNMATCHED);
    }

    post.setTitle(request.getTitle());
    post.setContent(request.getContent());
    post.setUpdatedTime(LocalDateTime.now());

    return PostDto.fromEntity(post);
  }

  @Transactional
  public PostDto deletePost(DeletePost.Request request) {
    User user = getUser(request.getUserId());
    Post post = getPost(request.getPostId());

    // 클라이언트의 user 정보가 게시글 작성자와 일치여부 확인
    if (post.getUser().getId() != request.getUserId()) {
      throw new AppException(ErrorCode.USERID_UNMATCHED);
    }

    // 내용은 프론트에서 안보이게 설정하고 데이터는 살려놓음
    post.setDeletedTime(LocalDateTime.now());

    return PostDto.fromEntity(post);
  }

  // 프로젝트 모든 구인 글 조회 ( 메인 화면, page 요소 추가 )
  public PagingResponse pagingPosts(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Post> projectPage = postRepository.findAll(pageable);

    List<Post> projectList = projectPage.getContent();
    List<PagingResponseDto.Response> postList = projectList.stream().map(
        Post -> PagingResponseDto.Response.fromEntity(Post)).collect(Collectors.toList());

    return PagingResponse.builder()
        .postList(postList)
        .pageNo(page)
        .pageSize(size)
        .totalElements(projectPage.getTotalElements())
        .totalPages(projectPage.getTotalPages())
        .build();
  }

  private User getUser(int userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USERID_INVALID));
  }

  private Post getPost(int postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new AppException(ErrorCode.POSTID_INVALID));
  }

}
