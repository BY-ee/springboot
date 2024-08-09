package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public String redirectToIndex() {
        return "redirect:/post/";
    }

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
        return "redirect:/post/";
    }

//    @GetMapping("/lists")
//    public String list(Model model) {
//        List<Post> dataList = postService.findAllByOrderByIdDesc();
//        model.addAttribute("dataList", dataList);
//        return "post/lists";
//    }

    @GetMapping("/lists")
    public String list(@RequestParam(value = "page") int page,
                       @RequestParam(value = "size") int size, Model model) {
         Page<Post> postPage = postService.getPosts(page, size);
         model.addAttribute("postPage", postPage);
         return "post/lists";
    }

    @GetMapping("/list")
    public String list(@RequestParam("id") Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long in, Model model) {
        Post post = postService.findById(in);
        model.addAttribute("post", post);
        return "post/update";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute Post post) {
        postService.updateById(id, post);
        return "redirect:/post/lists?page=0&size=5";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        postService.deleteById(id);
        return "redirect:/post/lists?page=0&size=5";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/post";
    }
}