package com.marondal.marondalgram.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marondal.marondalgram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
