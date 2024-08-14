package com.oraclejpa.service;

import com.oraclejpa.model.Post;
import com.oraclejpa.model.User;
import com.oraclejpa.repository.PostRepository;
import com.oraclejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
}
