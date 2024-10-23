package com.boardspace.controller;

import com.boardspace.dto.UserCredentials;
import com.boardspace.dto.UserDTO;
import com.boardspace.model.User;
import com.boardspace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    // 로그인
    @GetMapping("/login")
    public String index(HttpSession session,
                        @ModelAttribute User user, Model model) {
        if(session.getAttribute("loggedInUser") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "pages/user/login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        HttpSession session) {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(user.getUserId());
        userCredentials.setPassword(user.getPassword());

        Optional<User> authenticatedUser = userService.findUserByUserIdAndPassword(userCredentials);
        logger.info("authenticatedUser: {}", authenticatedUser);

        if (authenticatedUser.isPresent()) {
            session.setAttribute("loggedInUser", authenticatedUser.get());
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    // 회원가입
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("newUser", new User());
        return "pages/user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("newUser") User newUser) {
        // 이메일 수신동의와 약관동의는 추후 확인하는 로직 필요
        newUser.setEmailOptIn(true);
        newUser.setTermsAgreement(true);
        userService.insertUser(newUser);
        return "redirect:/";
    }

    // 아이디 찾기
    @GetMapping("/find-id")
    public String findId() {
        return "pages/user/find-id";
    }

    @PostMapping("/find-id")
    public String findId(@RequestParam("email") String email, Model model) {
        User user = userService.findUserByEmail(email).orElse(null);
        model.addAttribute("userid", user.getUserId());
        return "pages/user/return-id";
    }

    // 비밀번호 찾기
    @GetMapping("/find-pw")
    public String findPassword() {
        return "pages/user/find-pw";
    }

    @PostMapping("/find-pw")
    public String checkUserPassword(@RequestParam("userid") String userId,
                           @RequestParam("email") String email,
                           HttpSession session, Model model) {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(userId);
        userCredentials.setEmail(email);

        User user = userService.findUserByUserIdAndEmail(userCredentials).orElse(null);
        session.setAttribute("resetIdForPassword", user.getId());
        model.addAttribute("id", user.getId());
        return "pages/user/reset-pw";
    }

    // 비밀번호 초기화
    @PostMapping("/reset-pw")
    public String resetPassword(@RequestParam("password") String newPassword,
                          HttpSession session) {
        Long id = (Long) session.getAttribute("resetIdForPassword");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setPassword(newPassword);
        int result = userService.updatePasswordById(userDTO);

        session.removeAttribute("resetIdForPassword");
        return "redirect:/";
    }

    // 유저 프로필
    @GetMapping("/settings/profile")
    public String myPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        return "pages/user/user-info";
    }

    @GetMapping("/withdrawal")
    public String verifyPasswordForWithdrawal() {
        return "redirect:/user/verify-password?action=withdrawal";
    }

    @GetMapping("/user/withdrawal")
    public String moveWithdrawalView() {
        return "pages/user/withdrawal";
    }

    @PostMapping("/user/withdrawal")
    public String withdrawal(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        int result = userService.deleteUser(user);

        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/verify-password")
    public String verifyPassword(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        String password = user.getPassword();
        model.addAttribute("password", password);
        return "/pages/user/verify-password";
    }

    @PostMapping("/user/verify-password")
    public String verifyPassword(@RequestParam("password") String password,
                                 @RequestParam("action") String action,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
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
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}