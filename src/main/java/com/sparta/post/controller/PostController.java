package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.service.PostService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@ResponseBody + @Controller
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@RequestBody: xml이나 json 기반의 메시지를 사용하는 요청의 경우
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id,requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}