package com.oraclejpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class User {
    @Id
    private String id;
    private String password;
    private String email;
    private String nickname;
}
