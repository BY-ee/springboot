package com.oraclejpa.controller;

import com.oraclejpa.model.Post;
import com.oraclejpa.model.User;
import com.oraclejpa.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @ModelAttribute User user, Model model) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            return "redirect:/post";
        } else {
            model.addAttribute("user", user);
            return "user/index";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        User authenticatedUser = userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            redirectAttributes.addFlashAttribute("logInMessage", "로그인에 성공하였습니다.");
            return "redirect:/post/";
        } else {
            redirectAttributes.addFlashAttribute("logInMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String userId,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam String nickname) {
//        if(userService.existsByUserId(user.getUserId())) {
//            return "user/duplicate";
//        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickname(nickname);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/my-page")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/my-page";
    }

    @GetMapping("/update")
    public String update() {
        return "redirect:/user/verify-password?action=update";
    }

    @GetMapping("/user/update")
    public String editUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute User user) {
        String userId = user.getUserId();
        String email = user.getEmail();
        String nickname = user.getNickname();
        userService.updateEmailAndNicknameById(userId, email, nickname);
        return "redirect:/my-page";
    }

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "redirect:/user/verify-password?action=withdrawal";
    }

    @GetMapping("/user/verify-password")
    public String verifyPassword() {
        return "/user/verify-password";
    }

    public boolean checkUser(String password) {
        return userService.findUserByPassword(password) != null;
    }

    @PostMapping("/user/verify-password")
    public String verifyPassword(@RequestParam("password") String password,
                                 @RequestParam("action") String action) {
        if(checkUser(password)) {
            return handleAction(action);
        }
        return "redirect:/user/verify-password?action=" + action;
    }

    private String handleAction(String action) {
        switch(action) {
            case "update":
                return "/user/update";
            case "withdrawal":
                return "/user/withdrawal";
            default:
                return "redirect:/user/verify-password";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String redirectToHome() {
        return "redirect:/";
    }
}
