package com.mx.gtorreblanca.pointsaleadmin.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
