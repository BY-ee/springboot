package com.boardspace.repository;

import com.boardspace.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryUserRepository implements UserRepository {
    List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return true;
    }

    @Override
    public User findByUserIdAndPassword(String userId, String password) {
        return null;
    }

    @Override
    public User findByUserId(String userId) {
        return null;
    }

    @Override
    public User findUserByPassword(String password) {
        return null;
    }

    @Override
    public String findUserIdByEmail(String email) {
        return "";
    }

    @Override
    public Long findIdByUserIdAndEmail(String userId, String email) {
        return (long)0;
    }

    @Override
    public void updateEmailAndNicknameByCurrentNickname(String currentNickname, String email, String nickname) {

    }

    @Override
    public void updatePasswordById(String password, long id) {

    }
}
