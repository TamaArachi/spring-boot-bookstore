package com.mona.catalogservice.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

    //factory method to return custom message when forcode is called
    //this will be returned in the json response
    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException("Product with code not found : " +code);
    }




}
