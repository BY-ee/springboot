package com.boardspace.controller;

import com.boardspace.model.CommunityPost;
import com.boardspace.model.User;
import com.boardspace.service.CommunityBoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityBoardController {
    private final CommunityBoardService commBoardService;

    @GetMapping
    public String listCommPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        List<CommunityPost> commPostPage = commBoardService.findPosts(page, size);
        model.addAttribute("commPostPage", commPostPage);
        return "pages/board/community";
    }

    @GetMapping("/write")
    public String writePost() {
        return "pages/board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @ModelAttribute CommunityPost post) {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        post.setUserNickname(loggedInUser.getNickname());
        commBoardService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        CommunityPost post = commBoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "post";
    }

    @PostMapping("/update")
    public String updatePostById(@ModelAttribute CommunityPost post) {
        commBoardService.updatePostById(post);
        return "redirect:/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        commBoardService.deletePostById(id);
        return "redirect:/articles?page=1";
    }
}