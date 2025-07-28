package com.marondal.marondalgram.post.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.marondalgram.post.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentRestController {
	
	private final CommentService commentService;
	
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/post/comment/write-process")
	public Map<String, String> writeComment(
			@RequestParam int postId
			, @RequestParam String contents
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(commentService.createComment(postId, userId, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
