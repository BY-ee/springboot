package com.boardspace.controller;

import com.boardspace.dto.Pagination;
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

        // 메인 화면에서 각 게시판의 최신 5개 글 미리보기
        Pagination<QnAPost> qnAPosts = qnABoardService.findPosts(page, limit);
        Pagination<CommunityPost> commPosts = commBoardService.findPosts(page, limit);
        model.addAttribute("qnAPagination", qnAPosts);
        model.addAttribute("commPagination", commPosts);
        model.addAttribute("user", user);
        return "pages/board/index";
    }

    @GetMapping("/my-post")
    public String myPost(@RequestParam(value = "page", defaultValue = "1") int page,
                         HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        int size = 5;
        Pagination<CommunityPost> postPage = commBoardService.findPostsByUserId(page, size, 1);
        model.addAttribute("user", user);
        model.addAttribute("postPage", postPage);
        return "pages/user/my-post-v1";
    }
}