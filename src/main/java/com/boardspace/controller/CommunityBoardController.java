package com.boardspace.controller;

import com.boardspace.dto.Pagination;
import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
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
    public String listQnAPosts(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(required = false) Integer limit, Model model) {

        Pagination<CommunityPost> commPosts = commBoardService.findPosts(page, limit);
        model.addAttribute("commPagination", commPosts);
        return "pages/board/community";
    }

    @GetMapping("/write")
    public String writePost() {
        return "pages/board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @RequestBody CommunityPost post) {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        post.setUserId(loggedInUser.getId());
        post.setUserNickname(loggedInUser.getNickname());
        commBoardService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") long id,
                              Model model) {
        CommunityPost post = commBoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/post";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id,
                         Model model) {
        CommunityPost post = commBoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/update";
    }

    @PostMapping("/update")
    public String updatePostById(@ModelAttribute CommunityPost post) {
        commBoardService.updatePostById(post);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") long id) {
        commBoardService.deletePostById(id);
        return "redirect:/";
    }
}