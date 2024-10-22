package com.boardspace.controller;

import com.boardspace.dto.PostDTO;
import com.boardspace.dto.UserDTO;
import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
import com.boardspace.model.User;
import com.boardspace.service.CommunityBoardService;
import com.boardspace.service.QnABoardService;
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
    private final QnABoardService qnABoardService;
    private final CommunityBoardService commBoardService;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/qna/{id}")
    public ResponseEntity<PostDTO> getQnAPostById(@PathVariable long id) {
        PostDTO postDTO = qnABoardService.findPostById(id).orElseThrow(() -> new RuntimeException("QnAPost Not Found"));
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<CommunityPost> getCommunityPostById(@PathVariable long id) {
        CommunityPost commPost = commBoardService.findPostById(id).orElseThrow(() -> new RuntimeException("CommunityPost Not Found"));
        return ResponseEntity.ok(commPost);
    }
}
