package com.mx.gtorreblanca.pointsaleadmin.entities.product;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String code2;

    @Column(nullable = false, length = 145)
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "unit_purchase_id")
    private UnitProduct unitPurchase;

    @ManyToOne
    @JoinColumn(name = "unit_sales_id")
    private UnitProduct unitSales;

    @Column(nullable = false)
    private Integer factor;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeProduct type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryProduct> categories = new HashSet<>();
}
