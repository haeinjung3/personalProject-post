package com.sparta.post.service;



import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, JwtUtil jwtUtil) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        // RequestDto -> Entity
        Post post = new Post(requestDto, userDetails);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        // 해당 사용자인지 확인
        if (!post.getUser().getUsername().equals(user.getUsername())){
            throw new RejectedExecutionException();
        }
        // post 내용 수정
        post.update(requestDto);

        return id;
    }


    public Long deletePost(Long id, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        // 해당 사용자인지 확인
        if (!post.getUser().getUsername().equals(user.getUsername())){
            throw new RejectedExecutionException();
        }
        // post 삭제
        postRepository.delete(post);

        return id;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}