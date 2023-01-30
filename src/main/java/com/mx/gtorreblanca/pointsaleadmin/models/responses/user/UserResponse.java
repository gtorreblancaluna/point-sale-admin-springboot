package com.mx.gtorreblanca.pointsaleadmin.models.responses.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private Set<RoleResponse> roles;

}
