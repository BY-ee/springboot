package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String index() {
        return "post/index";
    }

    @PostMapping("/")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "post/com";
    }

    @PostMapping("/com")
    public String com() {
        return "post/index";
    }
}