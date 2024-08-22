package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.model.User;
import com.oraclejpa.service.PostService;
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

//    @GetMapping("")
//    public String redirectToIndex() {
//        return "redirect:/post/";
//    }

    @GetMapping("")
    public String index(Model model) {
        int page = 0;
        int size = 5;
        Page<Post> postPage = postService.getPosts(page, size);
        model.addAttribute("postPage", postPage);
        return "post/index-v1";
    }

    @GetMapping("/write")
    public String writePost() {
        return "post/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @ModelAttribute Post post) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String nickname = user.getNickname();
        postService.writePost(nickname, post);
        return "redirect:/post";
    }

//    @GetMapping("/lists")
//    public String list(Model model) {
//        List<Post> dataList = postService.findAllByOrderByIdDesc();
//        model.addAttribute("dataList", dataList);
//        return "post/lists";
//    }

    @GetMapping("/articles")
    public String articles(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        Page<Post> postPage = postService.getPosts(page - 1, size);
        model.addAttribute("postPage", postPage);
        return "post/articles-v1";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "post/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "post/update";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute Post post) {
        postService.updateById(id, post);
        return "redirect:/post/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        postService.deleteById(id);
        return "redirect:/post/articles?page=1";
    }

    @GetMapping("/my-post")
    public String myPost(@RequestParam(value = "page", defaultValue = "1") int page,
                         HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int size = 5;
        Page<Post> postPage = postService.findPostsByNickName(page - 1, size, user.getNickname());
        model.addAttribute("user", user);
        model.addAttribute("postPage", postPage);
        return "user/my-post";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/post";
    }
}