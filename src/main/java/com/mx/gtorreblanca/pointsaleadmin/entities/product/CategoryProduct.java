package com.mx.gtorreblanca.pointsaleadmin.entities.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories_product")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 145)
    private String name;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;
}
