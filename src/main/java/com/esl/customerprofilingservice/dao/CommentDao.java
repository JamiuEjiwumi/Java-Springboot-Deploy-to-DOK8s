package com.esl.customerprofilingservice.dao;

import com.esl.customerprofilingservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
