package com.marondal.marondalgram.post.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marondal.marondalgram.post.comment.domain.Comment;
import com.marondal.marondalgram.post.comment.dto.CommentView;
import com.marondal.marondalgram.post.comment.repository.CommentRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final UserService userService;
	
	public boolean addComment(int postId, int userId, String contents) {
		
		
		Comment comment = Comment.builder()
		.postId(postId)
		.userId(userId)
		.contents(contents)
		.build();
		
		try {
			commentRepository.save(comment);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	
	public List<CommentView> getCommentList(int postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			
			User user = userService.getUserById(comment.getUserId());
			
			CommentView commentView = CommentView.builder()
			.commentId(comment.getId())
			.contents(comment.getContents())
			.userId(comment.getUserId())
			.loginId(user.getLoginId())
			.build();
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
		
	}
	
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}
	

}
