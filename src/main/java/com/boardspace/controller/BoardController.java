package com.boardspace.controller;

import com.boardspace.model.CommunityBoard;
import com.boardspace.model.User;
import com.boardspace.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // 로그인 시 메인 페이지
    @GetMapping("")
    public String index(Model model) {
        int page = 1;
        int size = 5;
        Page<CommunityBoard> postPage = boardService.getPosts(page, size);
        model.addAttribute("postPage", postPage);
        return "board/index-v1";
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
        boardService.writePost(nickname, post);
        return "redirect:/board";
    }

    @GetMapping("/articles")
    public String articles(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        Page<CommunityBoard> postPage = boardService.getPosts(page, size);
        model.addAttribute("postPage", postPage);
        return "board/articles-v1";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        CommunityBoard post = boardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "board/detail-v1";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        CommunityBoard post = boardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "board/update";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute CommunityBoard post) {
        boardService.updateById(id, post);
        return "redirect:/board/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        boardService.deleteById(id);
        return "redirect:/board/articles?page=1";
    }

    @GetMapping("/my-post")
    public String myPost(@RequestParam(value = "page", defaultValue = "1") int page,
                         HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int size = 5;
        Page<CommunityBoard> postPage = boardService.findPostsByNickName(page - 1, size, user.getNickname());
        model.addAttribute("user", user);
        model.addAttribute("postPage", postPage);
        return "user/my-post-v1";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/board";
    }
}