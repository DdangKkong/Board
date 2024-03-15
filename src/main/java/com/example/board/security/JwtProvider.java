package com.example.board.security;

import com.example.board.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtProvider {

  private final UserService userService;

  private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60L; // 1 hour, long 타입이라 마지막에 L 붙임

  // 토큰 발급
  public static String generateToken(String userLoginId, String secretKey) {
    Claims claims = Jwts.claims();
    claims.put("userLoginId", userLoginId);

    var now = new Date();
    var expireDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now) // 토큰 생성 시간
        .setExpiration(expireDate) // 토큰 만료 시간
        .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘, 비밀키
        .compact();

  }

  // token 이 만료되었는지 체크
  public static boolean isExpired(String token, String secretKey) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration()
        .before(new Date());
  }
  // token 에서 userLoginId 를 가져옴
  public static String getUserLoginId(String token, String secretKey) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
        .get("userLoginId", String.class);
  }

}
