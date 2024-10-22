package com.boardspace.service;

import com.boardspace.dto.UserCredentials;
import com.boardspace.dto.UserDTO;
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

    public Optional<User> findUserById(long id) {
        return userMapper.findUserById(id);
    }

    public Optional<User> findUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }

    public Optional<User> findUserByUserIdAndPassword(UserCredentials userCredentials) {
        return userMapper.findUserByUserIdAndPassword(userCredentials);
    }

    public Optional<User> findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public Optional<User> findUserByUserIdAndEmail(UserCredentials userCredentials) {
        return userMapper.findUserByUserIdAndEmail(userCredentials);
    }

    public int updateEmailAndNicknameById(UserDTO user) {
        return userMapper.updateEmailAndNicknameById(user);
    }

    public int updatePasswordById(UserDTO userDTO) {
        return userMapper.updatePasswordById(userDTO);
    }
}
