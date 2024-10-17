package com.boardspace.service;

import com.boardspace.mapper.UserMapper;
import com.boardspace.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User insertUser(User newUser) {
        userMapper.insertUser(newUser);
        return newUser;
    }

    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    public Optional<User> findUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }

    public Optional<User> findUserByPassword(String password) {
        return userMapper.findUserByPassword(password);
    }

    public Optional<User> findUserByUserIdAndPassword(String userId, String password) {
        return userMapper.findUserByUserIdAndPassword(userId, password);
    }

    public Optional<String> findUserIdByEmail(String email) {
        return userMapper.findUserIdByEmail(email);
    }

    public Optional<Long> findIdByUserIdAndEmail(String userId, String email) {
        return userMapper.findIdByUserIdAndEmail(userId, email);
    }

    public boolean existsUserById(long id) {
        return userMapper.existsUserById(id);
    }

    public int updateEmailAndNicknameById(long id, String newEmail, String newNickname) {
        return userMapper.updateEmailAndNicknameById(id, newEmail, newNickname);
    }

    public int updatePasswordById(long id, String newPassword) {
        return userMapper.updatePasswordById(id, newPassword);
    }
}
