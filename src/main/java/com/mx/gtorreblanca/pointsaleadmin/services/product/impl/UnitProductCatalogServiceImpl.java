package com.mx.gtorreblanca.pointsaleadmin.services.product.impl;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.responses.CatalogResponse;
import com.mx.gtorreblanca.pointsaleadmin.repositories.product.UnitProductCatalogRepository;
import com.mx.gtorreblanca.pointsaleadmin.services.CatalogResponseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UnitProductCatalogServiceImpl implements CatalogResponseService {

    private final UnitProductCatalogRepository unitProductCatalogRepository;

    public UnitProductCatalogServiceImpl(UnitProductCatalogRepository unitProductCatalogRepository) {
        this.unitProductCatalogRepository = unitProductCatalogRepository;
    }

    @Override
    public List<CatalogResponse> getAll() throws BusinessException {
        var unitProducts = unitProductCatalogRepository.findAllByEnabledTrue();
        if (unitProducts.isEmpty()) {
            throw new NoDataFoundException();
        }

        return unitProducts.stream()
                .map(this::buildResponse)
                .toList();
    }

}
