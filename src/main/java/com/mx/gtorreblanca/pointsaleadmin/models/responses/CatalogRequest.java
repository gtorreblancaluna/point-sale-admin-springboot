package com.mx.gtorreblanca.pointsaleadmin.models.responses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogRequest {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 5, max = 180)
    private String name;
}
