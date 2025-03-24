package com.marondal.marondalgram.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marondal.marondalgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {

}
