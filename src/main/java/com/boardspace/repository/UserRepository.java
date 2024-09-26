package com.boardspace.repository;

import com.boardspace.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    // 유저 정보 저장
    User save(User user);

    // 유저 정보 삭제
    int delete(User user);

    // 유저 아이디로 사용자 조회
    Optional<User> findByUserId(String userId);

    // 비밀번호에 해당하는 사용자 조회
    Optional<User> findByPassword(String password);

    // 유저 아이디와 비밀번호에 해당하는 사용자 조회
    Optional<User> findByUserIdAndPassword(String userId, String password);

    // 이메일에 해당하는 유저 아이디 조회
    //@Query(value = "SELECT m1_0.user_id FROM member m1_0 WHERE m1_0.email=:email", nativeQuery = true)
    Optional<String> findUserIdByEmail(String email);

    // 유저 아이디와 이메일에 해당하는 고유 id 조회
    //@Query(value = "SELECT m1_0.id FROM member m1_0 WHERE m1_0.user_id=:userId AND m1_0.email=:email", nativeQuery = true)
    Optional<Long> findIdByUserIdAndEmail(String userId, String email);

    // 유저 아이디로 사용자 존재 여부 확인
    //@Query(value = "SELECT CASE WHEN EXISTS(SELECT 1 FROM member m1_0 WHERE m1_0.user_id=?)" +
    //        " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
    boolean existsById(long id);

    // 현재 닉네임에 해당하는 사용자의 이메일과 닉네임을 변경
    //@Modifying
    //@Query(value = "UPDATE member m1_0 SET m1_0.email=:email,m1_0.nickname=:nickname WHERE m1_0.nickname=:currentNickname", nativeQuery = true)
    int updateEmailAndNicknameById(long id, String email, String nickname);

    // 고유 id에 해당하는 사용자의 비밀번호를 변경
    //@Modifying
    //@Query(value = "UPDATE member m1_0 SET m1_0.password=:password WHERE m1_0.id=:id", nativeQuery = true)
    int updatePasswordById(String password, long id);
}
