package com.marondal.marondalgram.like.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	@CacheEvict(value = "postLikeCount", key = "#postId")
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
	
	@CacheEvict(value = "postLikeCount", key = "#postId")
	public boolean deleteLike(int postId, int userId) {
		Optional<Like> optionalLike = likeRepository.findByPostIdAndUserId(postId, userId);
		
		if(optionalLike.isPresent()) {
			
			Like like = optionalLike.get();
			
			try {				
				likeRepository.delete(like);
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
	}
	
	@Cacheable(value = "postLikeCount", key = "#postId")
	public int getLikeCount(int postId) {
		return likeRepository.countByPostId(postId);
	}
	
	public boolean isLikeByPostIdAndUserId(int postId, int userId) {
		return likeRepository.existsByPostIdAndUserId(postId, userId);
	}
	
	public void deleteLikeByPostId(int postId) {
		likeRepository.deleteByPostId(postId);
	}
	
	

}
