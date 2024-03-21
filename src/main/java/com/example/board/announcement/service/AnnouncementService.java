package com.example.board.announcement.service;

import com.example.board.announcement.controller.AnnouncementController;
import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.post.domain.Post;
import com.example.board.post.repository.PostRepository;
import com.example.board.user.repository.UserRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
  private final UserRepository userRepository;
  private final PostRepository postRepository;

  // 클라이언트에서 서버의 이벤트를 구독하기 위한 요청을 보냄
  public SseEmitter subscribe(int userId) {

    // 객체 생성
    SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

    // 연결
    try {
      sseEmitter.send(SseEmitter.event().name("connect"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 저장
    AnnouncementController.sseEmitterMap.put(userId, sseEmitter);

    // 연결 종료
    sseEmitter.onCompletion(() -> AnnouncementController.sseEmitterMap.remove(userId));
    sseEmitter.onTimeout(() -> AnnouncementController.sseEmitterMap.remove(userId));
    sseEmitter.onError((e) -> AnnouncementController.sseEmitterMap.remove(userId));

    return sseEmitter;
  }

  // 댓글 알림
  public void announceComment(int postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new AppException(ErrorCode.POSTID_INVALID));

    int userId = post.getUser().getId();
    if (AnnouncementController.sseEmitterMap.containsKey(userId)) {
      SseEmitter sseEmitterComment = AnnouncementController.sseEmitterMap.get(userId);
      // 알림 메세지 전송 및 종료
      try {
        sseEmitterComment.send(SseEmitter.event().name("accComment").data("댓글이 달렸습니다"));
      } catch (IOException e) {
        AnnouncementController.sseEmitterMap.remove(userId);
      }
    }

  }
}
