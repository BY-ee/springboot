package com.boardspace.service;

import com.boardspace.model.User;
import com.boardspace.repository.PostRepository;
import com.boardspace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void save(User user) {
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
    }

    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId) == 1;
    }

    public User findByUserIdAndPassword(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public String findUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }

//    public void updateEmailAndNicknameById(String userId, String email, String nickname) {
//        userRepository.updateEmailAndNicknameByUserId(userId, email, nickname);
//    }

    @Transactional
    public void updateNicknameForUniqueKeyAndForeignKey(String currentNickname, String email, String nickname) {
        postRepository.dropConstraint();
        userRepository.updateEmailAndNicknameByCurrentNickname(currentNickname, email, nickname);
        postRepository.updateNicknameByCurrentNickname(currentNickname, nickname);
        postRepository.addConstraint();
    }

    public User findUserByPassword(String password) {
        return userRepository.findUserByPassword(password);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
