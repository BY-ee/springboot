package com.oraclejpa.repository;

import com.oraclejpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT 1 FROM member u1_0 WHERE u1_0.user_id=?)" +
            " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
    int existsByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);
}
