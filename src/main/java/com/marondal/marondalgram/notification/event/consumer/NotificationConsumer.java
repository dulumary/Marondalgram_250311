package com.marondal.marondalgram.notification.event.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marondal.marondalgram.notification.event.dto.NotificationEvent;
import com.marondal.marondalgram.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NotificationConsumer {

	private final ObjectMapper objectMapper;
	private final NotificationService notificationService;
	
	@KafkaListener(
			topics = "notification-events",
			groupId = "notification-db-group"
			
	)
	public void consumer(String message) {
		
		try {
			NotificationEvent event = objectMapper.readValue(message, NotificationEvent.class);
			
			
			// 서비스 호출
			notificationService.createNotification(
					event.getType()
					, event.getActorId()
					, event.getTargetId()
					, event.getTargetType());
			
		} catch (JsonProcessingException e) {
			
			
		}
	}
	
}
