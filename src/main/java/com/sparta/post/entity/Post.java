package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto requestDto, UserDetailsImpl userDetails){
        this.username = userDetails.getUsername();  //null
        this.contents = requestDto.getContents();
        this.user = userDetails.getUser();
    }

    public void update(PostRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
