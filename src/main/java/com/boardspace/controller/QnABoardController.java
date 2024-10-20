package com.boardspace.controller;

import com.boardspace.dto.Pagination;
import com.boardspace.dto.PostDTO;
import com.boardspace.model.QnAPost;
import com.boardspace.model.User;
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
@RequestMapping("/qna")
public class QnABoardController {
    private final QnABoardService qnABoardService;

    @GetMapping
    public String listQnAPosts(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(required = false) Integer limit, Model model) {

        Pagination<QnAPost> qnAPosts = qnABoardService.findPosts(page, limit);
        model.addAttribute("qnAPagination", qnAPosts);
        return "pages/board/qna";
    }

    @GetMapping("/write")
    public String writePost() {
        return "pages/board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @RequestBody QnAPost post) {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        post.setUserId(loggedInUser.getId());
        post.setUserNickname(loggedInUser.getNickname());
        qnABoardService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") long id,
                              Model model) {
        qnABoardService.increaseViewCount(id);
        PostDTO post = qnABoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/post";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id,
                         Model model) {
        PostDTO post = qnABoardService.findPostById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/update";
    }

    @PostMapping("/update")
    public String updatePostById(@ModelAttribute QnAPost post) {
        qnABoardService.updatePostById(post);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") long id) {
        qnABoardService.deletePostById(id);
        return "redirect:/";
    }
}
