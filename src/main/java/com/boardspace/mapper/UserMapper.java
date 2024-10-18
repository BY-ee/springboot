package com.boardspace.mapper;

import com.boardspace.dto.UserCredentials;
import com.boardspace.dto.UserDTO;
import com.boardspace.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 회원가입
    int insertUser(User newUser);

    // 회원탈퇴
    int deleteUser(User user);

    // 유저 아이디로 사용자 조회
    Optional<User> findUserByUserId(String userId);

    // 유저 아이디와 비밀번호에 해당하는 사용자 조회
    Optional<User> findUserByUserIdAndPassword(UserCredentials userCredentials);

    // 유저 이메일로 사용자 조회
    Optional<User> findUserByEmail(String email);

    // 유저 아이디와 이메일로 사용자 조회
    Optional<User> findUserByUserIdAndEmail(UserCredentials userCredentials);

    // id와 일치하는 사용자의 이메일과 닉네임을 변경
    int updateEmailAndNicknameById(UserDTO user);

    // id와 일치하는 사용자의 비밀번호를 변경
    int updatePasswordById(UserDTO userDTO);
}
