package com.mx.gtorreblanca.pointsaleadmin.entities;

import com.mx.gtorreblanca.pointsaleadmin.constants.ColumnDefinitionConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance
@Getter
@Setter
@AllArgsConstructor
public class Catalog {

    public Catalog() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 145)
    private String name;
    @Column(
            nullable = false,
            columnDefinition = ColumnDefinitionConstant.TINYINT_DEFAULT_1_DEFINITION
    )
    private Boolean enabled;

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
