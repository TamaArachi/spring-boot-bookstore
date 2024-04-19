package com.mona.catalogservice.domain;

import java.math.BigDecimal;

class ProductMapper {

    static Product toProduct(ProductEntity entity){

        return new Product(entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getPrice());

    }
}
