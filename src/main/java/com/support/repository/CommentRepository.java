package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
