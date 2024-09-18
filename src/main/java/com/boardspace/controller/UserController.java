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

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String index(HttpServletRequest request,
                        @ModelAttribute User user, Model model) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "user/login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        Optional<User> authenticatedUser = userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());

        if (authenticatedUser.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser.get());
            redirectAttributes.addFlashAttribute("logInMessage", UserConstant.LOGIN_SUCCESS_MESSAGE);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("logInMessage", UserConstant.LOGIN_FAILURE_MESSAGE);
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "user/signup-v1";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User user) {
        // 이메일 수신동의와 약관동의는 추후 확인하는 로직 필요
        user.setEmailOptIn(true);
        user.setTermsAgreement(true);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/forgot-id")
    public String forgotId() {
        return "user/forgot-id";
    }

    @PostMapping("/forgot-id")
    public String forgotId(@RequestParam("email") String email, Model model) {
        String userId = userService.findUserIdByEmail(email).orElse(null);
        model.addAttribute("userid", userId);
        return "user/return-id";
    }

    @GetMapping("/forgot-pw")
    public String forgotPassword() {
        return "user/forgot-pw";
    }

    @PostMapping("/forgot-pw")
    public String checkUserPassword(@RequestParam("userid") String userId,
                           @RequestParam("email") String email,
                           HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long id = userService.findIdByUserIdAndEmail(userId, email).orElse(null);
        session.setAttribute("passwordResetId", id);
        model.addAttribute("id", id);
        return "user/reset-pw";
    }

    @PostMapping("/reset-pw")
    public String resetPassword(@RequestParam("password") String password,
                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("passwordResetId");
        session.removeAttribute("passwordResetId");
        userService.updatePasswordById(password, id);
        return "redirect:/";
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
        Long id = loggedInUser.getId();
        String email = user.getEmail();
        String nickname = user.getNickname();
        userService.updateEmailAndNicknameById(id, email, nickname);

        User updatedUser = userService.findByUserId(user.getUserId()).orElse(null);
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
            redirectAttributes.addFlashAttribute("errorMessage", UserConstant.INVALID_PASSWORD);
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
}