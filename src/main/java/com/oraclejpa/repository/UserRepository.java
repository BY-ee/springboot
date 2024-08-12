package com.oraclejpa.repository;

import com.oraclejpa.model.Post;
import com.oraclejpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT 1 FROM member u1_0 WHERE u1_0.user_id=?)" +
            " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
    int existsByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);

    User findByPassword(String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE member u1_0 SET u1_0.email=:email,u1_0.nickname=:nickname WHERE u1_0.user_id=:userId", nativeQuery = true)
    void updateEmailAndNicknameByUserId(String userId, String email, String nickname);
}
