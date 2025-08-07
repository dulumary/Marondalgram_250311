package com.marondal.marondalgram.notification.event.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
	
	private NotificationType type;
	private int actorId;
	private TargetType targetType;
	private int targetId;
	private LocalDateTime createdAt;	
	
}
