package com.example.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "token 이 만료되었습니다."),
  USERLOGINID_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
  USERLOGINID_NOTFOUND(HttpStatus.NOT_FOUND, "해당 아이디를 가진 유저를 찾을 수 없습니다."),
  USERLOGINID_INVALID(HttpStatus.CONFLICT, ""),
  PASSWORD_INVALID(HttpStatus.UNAUTHORIZED,  "패스워드를 잘못 입력하였습니다."),
  PASSWORD_NOT_CHANGED(HttpStatus.CONFLICT, ""),
  PASSWORD_NOT_CONFIRM(HttpStatus.CONFLICT, ""),
  PASSWORD_NOT_EQUALS(HttpStatus.CONFLICT, "입력한 패스워드와 확인용 패스워드가 일치하지 않습니다."),
  NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.")
      ;

  private final HttpStatus httpStatus;
  private final String message;

}
