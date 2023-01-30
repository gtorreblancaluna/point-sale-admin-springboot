package com.mx.gtorreblanca.pointsaleadmin.models.responses.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {

    private Integer id;
    private String name;
}
