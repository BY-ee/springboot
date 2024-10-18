package com.boardspace.controller;

import com.boardspace.model.QnAPost;
import com.boardspace.model.User;
import com.boardspace.dto.Pagination;
import com.boardspace.service.QnABoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnABoardController {
    private final QnABoardService qnABoardService;

    @GetMapping
    public String listQnAPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        Pagination<QnAPost> qnAPostPage = qnABoardService.getPosts(page, size);
        model.addAttribute("qnAPostPage", qnAPostPage);
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
        post.setNickname(loggedInUser.getNickname());
        qnABoardService.writePost(post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") long id,
                         Model model) {
        QnAPost post = qnABoardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/post";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id,
                         Model model) {
        QnAPost post = qnABoardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "pages/board/update";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") long id, @ModelAttribute QnAPost post) {
        qnABoardService.updateById(id, post);
        return "redirect:/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") long id) {
        qnABoardService.deleteById(id);
        return "redirect:/articles?page=1";
    }
}
