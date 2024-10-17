package com.boardspace.mapper;

import com.boardspace.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 유저 정보 저장
    int insertUser(User newUser);

    // 유저 정보 삭제
    int deleteUser(User user);

    // 유저 아이디로 사용자 조회
    Optional<User> findUserByUserId(String userId);

    // 비밀번호에 해당하는 사용자 조회
    Optional<User> findUserByPassword(String password);

    // 유저 아이디와 비밀번호에 해당하는 사용자 조회
    Optional<User> findUserByUserIdAndPassword(String userId, String password);

    // 이메일에 해당하는 유저 아이디 조회
    Optional<String> findUserIdByEmail(String email);

    // 유저 아이디와 이메일에 해당하는 고유 id 조회
    Optional<Long> findIdByUserIdAndEmail(String userId, String email);

    // 유저 아이디로 사용자 존재 여부 확인
    boolean existsUserById(long id);

    // id와 일치하는 사용자의 이메일과 닉네임을 변경
    int updateEmailAndNicknameById(long id, String newEmail, String newNickname);

    // id와 일치하는 사용자의 비밀번호를 변경
    int updatePasswordById(long id, String newPassword);
}
