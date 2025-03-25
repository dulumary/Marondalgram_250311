package com.marondal.marondalgram.like.service;

import org.springframework.stereotype.Service;

import com.marondal.marondalgram.like.domain.Like;
import com.marondal.marondalgram.like.repository.LikeRepository;

import jakarta.persistence.PersistenceException;

@Service
public class LikeService {
	
	private final LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public boolean addLike(int postId, int userId) {
		
		Like like = Like.builder()
		.postId(postId)
		.userId(userId)
		.build();
		
		try {			
			likeRepository.save(like);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	public int getLikeCount(int postId) {
		return likeRepository.countByPostId(postId);
	}
	
	

}
