package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String index() {
        return "post/index";
    }

    @GetMapping("/write")
    public String write() {
        return "post/write";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute Post post) {
        postService.save(post);
        return "post/comSave";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Post> dataList = postService.findAll();
        model.addAttribute("dataList", dataList);
        return "post/list";
    }

    @PostMapping("/list")
    public String listToIndex() {
        return "redirect:/";
    }

    @PostMapping("/comSave")
    public String comToIndex() {
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete() {
        return "post/delete";
    }

    @PostMapping("/delete")
    public String deletePostAll() {
        postService.deleteAll();
        return "post/comDelete";
    }
}