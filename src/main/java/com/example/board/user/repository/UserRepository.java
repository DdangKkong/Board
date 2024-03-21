package com.example.board.user.repository;

import com.example.board.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUserLoginId(String userLoginId);
  boolean existsByNickname(String nickname);
  Optional findByUserLoginId(String userLoginId);

}
