package com.boardspace.repository;

import com.boardspace.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemoryUserRepository implements UserRepository {
    private final List<User> users;

    // 유저 정보 저장
    @Override
    public User save(User user) {
        users.add(user);
        return users.get(users.size() - 1);
    }

    // 유저 정보 삭제
    @Override
    public int delete(User user) {
        int size = users.size();
        users.remove(user);
        return size - users.size();
    }

    // 유저 아이디로 사용자 조회
    @Override
    public Optional<User> findByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    // 비밀번호에 해당하는 사용자 조회
    @Override
    public Optional<User> findByPassword(String password) {
        return users.stream()
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }

    // 유저 아이디와 비밀번호에 해당하는 사용자 조회
    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
                .findAny();
    }

    // 이메일에 해당하는 유저 아이디 조회
    @Override
    public Optional<String> findUserIdByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .map(User::getUserId)
                .findAny();
    }

    // 유저 아이디와 이메일에 해당하는 고유 id 조회
    @Override
    public Optional<Long> findIdByUserIdAndEmail(String userId, String email) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId) && user.getEmail().equals(email))
                .map(User::getId)
                .findAny();
    }

    // 유저 아이디로 사용자 존재 여부 확인
    @Override
    public boolean existsById(long id) {
        return users.stream()
                .anyMatch(user -> user.getId().equals(id));
    }

    // 현재 닉네임에 해당하는 사용자의 이메일과 닉네임을 변경
    @Override
    public int updateEmailAndNicknameById(long id, String email, String nickname) {
        Optional<User> currentUser = users.stream().filter(user -> user.getId().equals(id)).findAny();
        if (currentUser.isPresent()) {
            currentUser.get().setEmail(email);
            currentUser.get().setNickname(nickname);
            return users.indexOf(currentUser.get());
        }
        return 0;
    }

    // 고유 id에 해당하는 사용자의 비밀번호를 변경
    @Override
    public int updatePasswordById(String password, long id) {
        Optional<User> currentUser = users.stream().filter(user -> user.getId().equals(id)).findAny();
        if (currentUser.isPresent()) {
            currentUser.get().setPassword(password);
            return users.indexOf(currentUser.get());
        }
        return 0;
    }
}
