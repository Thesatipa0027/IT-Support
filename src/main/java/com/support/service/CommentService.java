package com.support.service;

import java.util.List;

import com.support.entity.Comment;

public interface CommentService {

	Comment addComment(Comment comment);

	List<Comment> getAllComments();

	Comment getCommentByID(Long id);

}
