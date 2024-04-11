package com.example.Backend.domain.user;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
}
//회원가입 DTO
