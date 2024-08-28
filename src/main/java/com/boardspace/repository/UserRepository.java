package com.boardspace.repository;

import com.boardspace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT 1 FROM member u1_0 WHERE u1_0.user_id=?)" +
            " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
    int existsByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);

    User findUserByPassword(String password);

    @Modifying
    @Query(value = "UPDATE member u1_0 SET u1_0.email=:email,u1_0.nickname=:nickname WHERE u1_0.nickname=:currentNickname", nativeQuery = true)
    void updateEmailAndNicknameByCurrentNickname(String currentNickname, String email, String nickname);
}
