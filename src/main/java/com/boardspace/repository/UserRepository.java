package com.boardspace.repository;

import com.boardspace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT 1 FROM member m1_0 WHERE m1_0.user_id=?)" +
            " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
    int existsByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);

    User findUserByPassword(String password);

    @Query(value = "SELECT m1_0.user_id FROM member m1_0 WHERE m1_0.email=:email", nativeQuery = true)
    String findUserIdByEmail(String email);

    @Query(value = "SELECT m1_0.id FROM member m1_0 WHERE m1_0.user_id=:userId AND m1_0.email=:email", nativeQuery = true)
    Long findIdByUserIdAndEmail(String userId, String email);

    @Modifying
    @Query(value = "UPDATE member m1_0 SET m1_0.email=:email,m1_0.nickname=:nickname WHERE m1_0.nickname=:currentNickname", nativeQuery = true)
    void updateEmailAndNicknameByCurrentNickname(String currentNickname, String email, String nickname);

    @Modifying
    @Query(value = "UPDATE member m1_0 SET m1_0.password=:password WHERE m1_0.id=:id", nativeQuery = true)
    void updatePasswordById(String password, long id);
}
