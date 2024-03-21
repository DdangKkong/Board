package com.example.board.announcement.controller;

import com.example.board.announcement.service.AnnouncementService;
import com.example.board.user.domain.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {

  private final AnnouncementService announcementService;
  // userId 를 key 값으로 하는 sseEmitterMap
  public static Map<Integer, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

  @GetMapping
  public SseEmitter message(@AuthenticationPrincipal User user) {
    int userId = user.getId();
    SseEmitter sseEmitter = announcementService.subscribe(userId);

    return sseEmitter;
  }

}
