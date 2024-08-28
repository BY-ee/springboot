package com.boardspace.controller;

import com.boardspace.model.User;
import com.boardspace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
            return "user/index-v1";
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
            return "redirect:/post";
        } else {
            redirectAttributes.addFlashAttribute("logInMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "user/signup-v1";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String userId,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam String nickname) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickname(nickname);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgot(Model model) {
        
    }

    @GetMapping("/my-page")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/my-page-v1";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        String currentNickname = loggedInUser.getNickname();
        String email = user.getEmail();
        String nickname = user.getNickname();
        userService.updateNicknameForUniqueKeyAndForeignKey(currentNickname, email, nickname);

        User updatedUser = userService.findByUserId(user.getUserId());
        session.setAttribute("user", updatedUser);
        return "redirect:/my-page";
    }

    @GetMapping("/withdrawal")
    public String verifyPasswordForWithdrawal() {
        return "redirect:/user/verify-password?action=withdrawal";
    }

    @GetMapping("/user/withdrawal")
    public String moveWithdrawalView() {
        return "user/withdrawal";
    }

    @PostMapping("/user/withdrawal")
    public String withdrawal(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        userService.deleteUser(user);
        session.setAttribute("user", null);
        return "redirect:/";
    }

    @GetMapping("/user/verify-password")
    public String verifyPassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String password = user.getPassword();
        model.addAttribute("password", password);
        return "/user/verify-password";
    }

    @PostMapping("/user/verify-password")
    public String verifyPassword(@RequestParam("password") String password,
                                 @RequestParam("action") String action,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(password.equals(user.getPassword())) {
            return handleAction(action);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "잘못된 비밀번호가 입력되었습니다.");
            return "redirect:/user/verify-password?action=" + action;
        }
    }

    private String handleAction(String action) {
        switch(action) {
            case "update":
                return "redirect:/user/update";
            case "withdrawal":
                return "redirect:/user/withdrawal";
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