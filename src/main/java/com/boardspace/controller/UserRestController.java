package com.boardspace.controller;

import com.boardspace.dto.UserDTO;
import com.boardspace.model.User;
import com.boardspace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/current-user")
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    @PostMapping("/user/update")
    public ResponseEntity<Integer> updateUser(@RequestBody User user,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO userDTO = new UserDTO();

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        userDTO.setId(loggedInUser.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());

        int result = userService.updateEmailAndNicknameById(userDTO);

        User updatedUser = userService.findUserByUserId(user.getUserId()).orElse(null);
        session.setAttribute("loggedInUser", updatedUser);
        return ResponseEntity.ok(result);
    }
}
