package com.boardspace.model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class User {
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private boolean emailOptIn;
    private boolean termsAgreement;
    private Timestamp createdAt;
}
