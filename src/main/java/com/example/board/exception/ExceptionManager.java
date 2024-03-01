package com.example.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Controller 에서 발생하는 Custom error 를 잡아줌
@RestControllerAdvice
public class ExceptionManager {

  @ExceptionHandler(AppException.class)
  // 예외 발생시 출력할 요소들 세팅
  public ResponseEntity<?> appExceptionHandler(AppException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(e.getErrorCode().name() + " " + e.getErrorCode().getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

}
