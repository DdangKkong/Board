package com.example.board.comment.repository;

import com.example.board.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

  List<Comment> findAllByPostId(int postId);
}
