package com.oraclejpa.service;

import com.oraclejpa.model.User;
import com.oraclejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}
