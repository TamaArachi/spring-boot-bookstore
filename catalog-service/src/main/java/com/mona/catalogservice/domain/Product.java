package com.mona.catalogservice.domain;

import java.math.BigDecimal;

public record Product(String code, String name, String description, String imageUrl, BigDecimal price) {
    public static class ProductNotFoundException extends RuntimeException{

        public ProductNotFoundException(String message){
            super(message);
        }

        //factory method to return custom message when forcode is called
        public static ProductNotFoundException forCode(String code){
            return new ProductNotFoundException("Product with code not found : " +code);
        }




    }
}
