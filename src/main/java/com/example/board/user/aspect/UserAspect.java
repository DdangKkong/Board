package com.example.board.user.aspect;

import com.example.board.exception.AppException;
import com.example.board.exception.ErrorCode;
import com.example.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect implements UserDetailsService {
  private final UserRepository userRepository;

  // 알림을 위해 회원 정보를 불러오려면 UserDetails 로 형변환 해야함
  @SneakyThrows
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) userRepository.findByUserLoginId(username)
        .orElseThrow(() -> new AppException(ErrorCode.USERLOGINID_NOTFOUND));
  }

}
