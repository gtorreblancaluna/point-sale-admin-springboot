package com.mx.gtorreblanca.pointsaleadmin.entities.product;

import com.mx.gtorreblanca.pointsaleadmin.constants.ColumnDefinitionConstant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = ColumnDefinitionConstant.PRODUCTS_TABLE_NAME)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String code;

    @Column(length = 80)
    private String code2;

    @Column(nullable = false, length = 145)
    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_purchase_id")
    private UnitProductCatalog unitPurchase;

    @ManyToOne
    @JoinColumn(name = "unit_sales_id")
    private UnitProductCatalog unitSales;

    @Column(nullable = false)
    private Integer factor;

    @Column(
            nullable = false,
            columnDefinition = ColumnDefinitionConstant.TINYINT_DEFAULT_1_DEFINITION
    )
    private Boolean enabled;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeProductCatalog type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryProductCatalog> categories = new HashSet<>();

    @PrePersist
    void preInsert() {
        this.enabled = true;
    }

    @PreUpdate
    void preUpdate() {
        if (this.enabled == null)
            this.enabled = true;
    }
}
