package com.marondal.marondalgram.notification.event.dto;

public enum NotificationType {
	
	COMMENT_ON_POST(101, "님이 회원님의 글에 댓글을 남겼어요!"),
	LIKE_ON_POST(102, "님이 회원님의 글을 좋아합니다!");
	
	
	private final int code;
	private final String messageTemplate;
	
	NotificationType(int code, String messageTemplate) {
		this.code = code;
		this.messageTemplate = messageTemplate;
	}

	public String message(String actor) {
		return actor + messageTemplate;
	}

	public int getCode() {
		return code;
	}
	
}
