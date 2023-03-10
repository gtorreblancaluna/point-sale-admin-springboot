package com.mx.gtorreblanca.pointsaleadmin.models.requests.user;

import com.mx.gtorreblanca.pointsaleadmin.constants.ValidationMessageConstant;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserRequest {

    @Size(min = 5, max = 200, message = ValidationMessageConstant.USER_NAME)
    @NotEmpty(message = ValidationMessageConstant.USER_NAME)
    private String name;

    @Size(min = 5, max = 200, message = ValidationMessageConstant.USER_LAST_NAME)
    @NotEmpty(message = ValidationMessageConstant.USER_LAST_NAME)
    private String lastName;

    @Email(message = ValidationMessageConstant.USER_EMAIL)
    private String email;

    @Size(min = 5, max = 200, message = ValidationMessageConstant.USER_NAME)
    @NotEmpty(message = ValidationMessageConstant.USER_NAME)
    private String username;

    @Size(min=10,max=10)
    @Pattern(regexp=ValidationMessageConstant.REGEX_PATTERN_PHONE_NUMBER)
    @NotEmpty(message = ValidationMessageConstant.USER_PHONE_NUMBER_NOT_EMPTY)
    private String phoneNumber;

    @NotEmpty(message = ValidationMessageConstant.USER_PASSWORD)
    private String password;

    private List<RoleRequest> roles;
}
