### 변경사항
<!-- 이 PR에서 어떤점들이 변경되었는지 기술해주세요. 가급적이면 as-is, to-be를 활용해서 작성해주세요.  -->
**AS-IS**

- 알림 Announcement 구현
    - SseEmitter 사용.
    - 게시글에 댓글이 달릴 시, 게시글 작성자에게 알림 메세지 발송.
    - postman 사용하여 테스트.
    - @AuthenticationPrincipal 을 사용하여 user 정보를 token 을 통해 가져오는데, User(Entity) 로는 가져와지지 않아서 UserAspect.java 를 통해 UserDetails 로 변환함. 

**TO-BE**

- test code 작성

### 테스트
<!-- 본 변경사항이 테스트가 되었는지 기술해주세요 --> 
- [ ] 테스트 코드
- [X] API 테스트 