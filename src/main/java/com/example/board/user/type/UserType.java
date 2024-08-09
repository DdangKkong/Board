package com.example.board.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

  USER("ROLE_USER");



  private final String description;
}
