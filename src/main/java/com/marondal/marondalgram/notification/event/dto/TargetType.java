package com.marondal.marondalgram.notification.event.dto;

public enum TargetType {
	
	Post(1, "게시글");
	
	private final int code;
	private final String name;
	
	TargetType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	
}
