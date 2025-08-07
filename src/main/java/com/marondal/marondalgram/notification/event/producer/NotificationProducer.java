package com.marondal.marondalgram.notification.event.producer;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marondal.marondalgram.notification.event.dto.NotificationEvent;
import com.marondal.marondalgram.notification.event.dto.NotificationType;
import com.marondal.marondalgram.notification.event.dto.TargetType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationProducer {
	
	public static final String TOPIC = "notification-events";
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	
	public void sendLikeToPostNotification(
			int actorId
			, int targetId) {
		
		send(new NotificationEvent(
				NotificationType.LIKE_ON_POST
				, actorId
				, TargetType.Post
				, targetId
				, LocalDateTime.now()));
	}
	
	public void sendCommentToPostNotification(
			int actorId
			, int targetId) {
		
		send(new NotificationEvent(
				NotificationType.COMMENT_ON_POST
				, actorId
				, TargetType.Post
				, targetId
				, LocalDateTime.now()));
	}
	
	public void send(NotificationEvent event) {
		
		try {
			String message = objectMapper.writeValueAsString(event);
			
			kafkaTemplate.send(TOPIC, message);
			
		} catch (JsonProcessingException e) {
			
		}
		
		
	}

	

}
