package com.mx.gtorreblanca.pointsaleadmin.repositories;

import com.mx.gtorreblanca.pointsaleadmin.entities.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;

@NoRepositoryBean
public interface CatalogRepository<T extends Catalog> extends CrudRepository<T, Long> {
    List<T> findAllByEnabledTrue ();
}
