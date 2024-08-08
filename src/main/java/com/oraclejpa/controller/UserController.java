package com.oraclejpa.controller;

import com.oraclejpa.model.User;
import com.oraclejpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "user/index";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/post";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        return "redirect:/post";
    }
}
