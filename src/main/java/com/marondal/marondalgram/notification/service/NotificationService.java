package com.marondal.marondalgram.notification.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.notification.domain.Notification;
import com.marondal.marondalgram.notification.event.dto.NotificationType;
import com.marondal.marondalgram.notification.event.dto.TargetType;
import com.marondal.marondalgram.notification.repository.NotificationRepository;
import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.service.PostService;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationService {
	
	private final NotificationRepository notificationRepository;
	private final UserService userService;
	private final PostService postService;
	
	public boolean createNotification(
			NotificationType type
			, int actorId
			, int targetId
			, TargetType targetType) {
		
		
		User user = userService.getUserById(actorId);
		Post post = postService.getPost(targetId);
		
		Notification notification = Notification.builder()
		.receiverId(post.getUserId())
		.type(type.getCode())
		.message(type.message(user.getLoginId()))
		.build();
		
		try {
			notificationRepository.save(notification);
		} catch(DataAccessException e) {
			return false;
		}
		
		return true;
	}
	
	
	public List<Notification> getNotificationList(int userId) {
		return notificationRepository.findByReceiverId(userId, Sort.by(Sort.Order.desc("id")));
	}

}
