package com.mona.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace = none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        })
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testfindProducts() {
        List<ProductEntity> productsList = productRepository.findAll();
        assertThat(productsList).hasSize(15);
    }

    @Test
    public void testfindProductsByCode(){
        String code = "P108";
       Optional<ProductEntity> codeEntity =  productRepository.findByCode(code);
       assertThat(codeEntity).isNotEmpty();
       assertThat(codeEntity.get().getCode()).isEqualTo("P108");
        assertThat(codeEntity.get().getDescription()).isEqualTo("This beloved book by E. B. White, author of Stuart Little and The Trumpet of the Swan, is a classic of children's literatures");
    }


    @Test
    void testshouldReturnEmptyForInvalidProductCode(){
        assertThat(productRepository.findByCode("invalidcode")).isEmpty();
    }
}
