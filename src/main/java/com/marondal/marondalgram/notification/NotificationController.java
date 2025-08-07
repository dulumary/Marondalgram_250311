package com.marondal.marondalgram.notification;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marondal.marondalgram.notification.domain.Notification;
import com.marondal.marondalgram.notification.service.NotificationService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NotificationController {
	
	private final NotificationService notificationService;
	
	@GetMapping("/notification/list")
	public String alarmList(
			HttpSession session
			, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Notification> notificationList = notificationService.getNotificationList(userId);
		
		model.addAttribute("notiList", notificationList);
		
		return "notification/list";
	}

}
