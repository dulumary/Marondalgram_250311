package com.marondal.marondalgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marondal.marondalgram.post.dto.CardView;
import com.marondal.marondalgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/timeline-view")
	public String timeline(
			HttpSession session
			,Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardList = postService.getPostList(userId);
		
		model.addAttribute("cardList", cardList);
		
		return "post/timeline";
	}
	

}
