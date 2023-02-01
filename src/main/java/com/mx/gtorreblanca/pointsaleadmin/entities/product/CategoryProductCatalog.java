package com.mx.gtorreblanca.pointsaleadmin.entities.product;

import com.mx.gtorreblanca.pointsaleadmin.constants.ColumnDefinitionConstant;
import com.mx.gtorreblanca.pointsaleadmin.entities.Catalog;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ColumnDefinitionConstant.CATEGORIES_PRODUCT_CATALOG_TABLE_NAME)
@Getter
@NoArgsConstructor
public class CategoryProductCatalog extends Catalog {
    @Builder
    public CategoryProductCatalog(Long id, String name, Boolean enabled) {
        super(id, name, enabled);
    }
}