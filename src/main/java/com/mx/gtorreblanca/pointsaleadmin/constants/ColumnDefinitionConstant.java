package com.mx.gtorreblanca.pointsaleadmin.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnDefinitionConstant {

    public static final String CREATED_AT_COLUMN_NAME = "created_at";
    public static final String UPDATED_AT_COLUMN_NAME = "updated_at";
    public static final String TIMESTAMP_DEFINITION = "TIMESTAMP";
    public static final String TINYINT_DEFAULT_1_DEFINITION = "TINYINT(1) DEFAULT 1";
    public static final String USERS_TABLE_NAME = "users";
    public static final String PRODUCTS_TABLE_NAME = "products";
}
