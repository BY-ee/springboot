package com.boardspace.service;

import com.boardspace.model.User;
import com.boardspace.repository.MemoryUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final MemoryUserRepository userRepository;
    private static long id = 1;

    public void saveUser(User user) {
        user.setId(id);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        id++;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public Optional<User> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }

    public Optional<String> findUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }

    public Optional<Long> findIdByUserIdAndEmail(String userId, String email) {
        return userRepository.findIdByUserIdAndEmail(userId, email);
    }

    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    public int updateEmailAndNicknameById(long id, String email, String nickname) {
        return userRepository.updateEmailAndNicknameById(id, email, nickname);
    }

    public void updatePasswordById(String password, long id) {
        userRepository.updatePasswordById(password, id);
    }
}
