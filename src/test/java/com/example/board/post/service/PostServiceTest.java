package com.example.board.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.board.post.domain.Post;
import com.example.board.post.dto.CreatePost;
import com.example.board.post.dto.CreatePost.Request;
import com.example.board.post.dto.PostDto;
import com.example.board.post.repository.PostRepository;
import com.example.board.user.domain.User;
import com.example.board.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  @InjectMocks
  private PostService postService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private PostRepository postRepository;

  @Test
  void createPost() {

    // given
    User user = User.builder().nickname("wonwoo").build();
    user.setId(1);
    given(userRepository.findById(any(Integer.class))).willReturn(Optional.of(user));
    given(postRepository.save(any())).willReturn(Post.builder()
                                                    .user(user)
//                                                    .title(anyString())
//                                                    .content(anyString())
//                                                    .createdTime(any())
                                                    .build());
    ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);

    // when
    CreatePost.Request request = Request.builder().userId(1).title("title").content("content").build();
    PostDto postDto = postService.createPost(request);

    // then
    verify(postRepository).save(captor.capture());
    assertEquals(1, postDto.getUserId());
    assertEquals("title", captor.getValue().getTitle());
    assertEquals("content", captor.getValue().getContent());

  }



}