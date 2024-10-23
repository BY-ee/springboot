package com.boardspace.controller;

import com.boardspace.dto.Pagination;
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

    // 세션의 유저 데이터 반환
    @GetMapping("/users/session")
    public ResponseEntity<User> getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return ResponseEntity.ok(user);
    }

    // 세션의 유저 id 데이터 반환
    @GetMapping("/users/session/id")
    public ResponseEntity<Long> getSessionId(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return ResponseEntity.ok(user.getId());
    }

    // 유저의 이메일, 닉네임 수정
    @PostMapping("/user/update")
    public ResponseEntity<Integer> updateUser(@RequestBody User user,
                             HttpSession session) {
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

    // 특정 유저 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return ResponseEntity.ok(user);
    }

    // 특정 유저의 qna 게시글 조회
    @GetMapping("/users/{userId}/qna")
    public ResponseEntity<Pagination<QnAPost>> findQnAPostsByUserId(@PathVariable long userId) {
        Pagination<QnAPost> qnAPosts = qnABoardService.findPostsByUserId(1, 5, userId);
        return ResponseEntity.ok(qnAPosts);
    }

    // 특정 유저의 커뮤니티 게시글 조회
    @GetMapping("/users/{userId}/community")
    public ResponseEntity<Pagination<CommunityPost>> findCommunityPostsByUserId(@PathVariable long userId) {
        Pagination<CommunityPost> commPosts = commBoardService.findPostsByUserId(1, 5, userId);
        return ResponseEntity.ok(commPosts);
    }
}