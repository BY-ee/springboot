package com.boardspace.controller;

import com.boardspace.model.CommunityBoard;
import com.boardspace.model.User;
import com.boardspace.service.CommunityBoardService;
import com.boardspace.service.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityBoardController {
    private final CommunityBoardService commBoardService;

    @GetMapping
    public String listCommPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        Pagination<CommunityBoard> commPostPage = commBoardService.getPosts(page, size);
        model.addAttribute("commPostPage", commPostPage);
        return "board/community";
    }

    @GetMapping("/write")
    public String writePost() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @ModelAttribute CommunityBoard post) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String nickname = user.getNickname();
        commBoardService.writePost(nickname, post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        CommunityBoard post = commBoardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "post";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute CommunityBoard post) {
        commBoardService.updateById(id, post);
        return "redirect:/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        commBoardService.deleteById(id);
        return "redirect:/articles?page=1";
    }
}