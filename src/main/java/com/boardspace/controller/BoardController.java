package com.boardspace.controller;

import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
import com.boardspace.model.User;
import com.boardspace.service.CommunityBoardService;
import com.boardspace.service.QnABoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final CommunityBoardService commBoardService;
    private final QnABoardService qnABoardService;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        int page = 1;
        int limit = 5;

        // 각 게시글을 size 별로 나눠서 뷰에 전달
        List<QnAPost> qnAPostPage = qnABoardService.findPosts(page, limit);
        List<CommunityPost> commPostPage = commBoardService.findPosts(page, limit);
        model.addAttribute("qnAPostPage", qnAPostPage);
        model.addAttribute("commPostPage", commPostPage);
        model.addAttribute("user", user);
        return "pages/board/index";
    }

    @GetMapping("/write")
    public String writePost() {
        return "pages/board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @ModelAttribute CommunityPost post) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        String nickname = user.getNickname();
        commBoardService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        CommunityPost post = commBoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "pages/board/post";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute CommunityPost post) {
        commBoardService.updatePostById(post);
        return "redirect:/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        commBoardService.deletePostById(id);
        return "redirect:/articles?page=1";
    }

    @GetMapping("/my-post")
    public String myPost(@RequestParam(value = "page", defaultValue = "1") int page,
                         HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        int size = 5;
        List<CommunityPost> postPage = commBoardService.findPostsByUserId(page, size, 1);
        model.addAttribute("user", user);
        model.addAttribute("postPage", postPage);
        return "pages/user/my-post-v1";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/";
    }
}