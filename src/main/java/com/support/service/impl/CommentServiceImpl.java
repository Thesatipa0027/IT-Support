package com.support.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.support.entity.Comment;
import com.support.repository.CommentRepository;
import com.support.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public Comment getCommentByID(Long id) {
		return commentRepository.getById(id);
	}



}
