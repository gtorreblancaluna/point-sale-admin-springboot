package com.mx.gtorreblanca.pointsaleadmin.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogResponse {

    private Long id;
    private String name;
}
