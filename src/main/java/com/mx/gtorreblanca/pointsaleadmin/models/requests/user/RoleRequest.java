package com.mx.gtorreblanca.pointsaleadmin.models.requests.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleRequest {

    private Integer id;
    private String name;
}
