package com.sparta.post.repository;

import com.sparta.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByUsername(String username);
    //2주차 과제
    List<Post> findByContentsContainsOrderByModifiedAtDesc(String keyword);
}
