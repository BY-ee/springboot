package com.boardspace.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private boolean emailOptIn;
    private boolean termsAgreement;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
