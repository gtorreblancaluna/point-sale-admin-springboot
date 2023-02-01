package com.mx.gtorreblanca.pointsaleadmin.repositories.product;

import com.mx.gtorreblanca.pointsaleadmin.entities.product.UnitProductCatalog;
import com.mx.gtorreblanca.pointsaleadmin.repositories.CatalogRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UnitProductCatalogRepository extends CatalogRepository<UnitProductCatalog> {
}
