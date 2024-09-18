package com.boardspace.controller;

import com.boardspace.model.QnABoard;
import com.boardspace.model.User;
import com.boardspace.service.Pagination;
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
        Pagination<QnABoard> qnAPostPage = qnABoardService.getPosts(page, size);
        model.addAttribute("qnAPostPage", qnAPostPage);
        return "board/qna";
    }

    @GetMapping("/write")
    public String writePost() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writePost(HttpServletRequest request,
                            @ModelAttribute QnABoard post) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String nickname = user.getNickname();
        qnABoardService.writePost(nickname, post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        QnABoard post = qnABoardService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("page", page);
        return "board/detail-v1";
    }

    @PostMapping("/update")
    public String updatePostById(@RequestParam("id") Long id, @ModelAttribute QnABoard post) {
        qnABoardService.updateById(id, post);
        return "redirect:/articles?page=1";
    }

    @PostMapping("/delete")
    public String deletePostById(@RequestParam("id") Long id) {
        qnABoardService.deleteById(id);
        return "redirect:/articles?page=1";
    }
}
