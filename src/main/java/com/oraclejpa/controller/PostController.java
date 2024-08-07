package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String writePost() {
        return "post/write";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "post/comSave";
    }

//    @GetMapping("/lists")
//    public String list(Model model) {
//        List<Post> dataList = postService.findAllByOrderByIdDesc();
//        model.addAttribute("dataList", dataList);
//        return "post/lists";
//    }

    @GetMapping("/lists")
    public Page<Post> list(@RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {
        return postService.getPosts(page, size);
    }

    @GetMapping("/list")
    public String list(@RequestParam("id") Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "post/update";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute Post post) {
        postService.updateById(id, post);
        return "redirect:/lists";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        postService.deleteById(id);
        return "redirect:/lists";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/";
    }
}