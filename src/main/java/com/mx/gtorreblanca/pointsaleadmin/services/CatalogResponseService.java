package com.mx.gtorreblanca.pointsaleadmin.services;

import com.mx.gtorreblanca.pointsaleadmin.entities.product.UnitProductCatalog;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.models.responses.CatalogResponse;

import java.util.List;

public interface CatalogResponseService {

    default CatalogResponse buildResponse (UnitProductCatalog unitProductCatalog) {
        return CatalogResponse.builder()
                .id(unitProductCatalog.getId())
                .name(unitProductCatalog.getName())
                .build();
    }

    List<CatalogResponse> getAll () throws BusinessException;
}
