package com.woowang.myboard.repository;

import com.woowang.myboard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {


}
