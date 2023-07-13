//package com.sparta.post.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "comment")
//@NoArgsConstructor
//public class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "comment", nullable = false)
//    private String comment;
//
//    @ManyToOne
//    private User user;
//
//    @ManyToOne
//    private Post post;
//}
