package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 보여주는 메소드
    @GetMapping("/")
    public String post() {
        return "post/new";
    }
    // 게시글 폼 제출시 db에 데이터 insert하는 메소드
    @PostMapping("/")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "post/hi";
    }
}