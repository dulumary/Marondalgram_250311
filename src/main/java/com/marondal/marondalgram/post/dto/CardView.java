package com.marondal.marondalgram.post.dto;

import lombok.Builder;
import lombok.Getter;

// DTO
// 게시글 하나를 표현하는 카드 화면을 구성하기 위해 필요한 값들을 관리하기위한 클래스

@Builder
@Getter
public class CardView {
	
	private int postId;
	
	private String contents;
	private String imagePath;
	
	private int userId;
	private String loginId;
	
	private int likeCount;
	
	// 로그인한 사용자의 좋아요 여부 
	private boolean isLike;
	// 댓글 목록
	private List<Comment> commentList;
	

}
