package com.boardspace.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("UserDTO")
public class UserDTO {
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private boolean emailOptIn;
    private boolean termsAgreement;
}
