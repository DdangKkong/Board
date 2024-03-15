package com.example.board.security;

import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j // log.~~ 사용하기 위해 추가
public class JwtFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final String secretKey;

  // 페이지 접근 권한 부여, 회원가입과 로그인 시에는 Header 에 Authorization 을 넣지 않으니까 당연히 null 이 뜨지 않을까?
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization : {}", authorization);

    // token 확인
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      log.error("authorization 을 잘못 보냈습니다.");
      filterChain.doFilter(request, response);
      return;
    }

    // token 꺼내기
    String token = authorization.split(" ")[1];

    // token expired 체크
    if (JwtProvider.isExpired(token, secretKey)) {
      log.error("token 이 만료 되었습니다.");
      filterChain.doFilter(request, response);
      throw new AppException(ErrorCode.TOKEN_EXPIRED);
    }

    // token 에서 userLoginId 꺼내기
    String userLoginId = JwtProvider.getUserLoginId(token, secretKey);
    log.info("userLoginId:{}", userLoginId);

    // 권한 부여
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userLoginId, null, List.of(new SimpleGrantedAuthority("USER")));
    // Detail
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);

  }
}
