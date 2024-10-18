package com.boardspace.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("CredentialsDTO")
public class UserCredentials {
    private String userId;
    private String password;
    private String email;
}
