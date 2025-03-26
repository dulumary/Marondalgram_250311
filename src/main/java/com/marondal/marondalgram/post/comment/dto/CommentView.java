package com.marondal.marondalgram.post.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentView {
	
	private int commentId;
	private String contents;
	
	private int userId;
	private String loginId;

}
