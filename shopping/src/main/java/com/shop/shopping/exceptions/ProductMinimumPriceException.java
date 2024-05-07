package com.shop.shopping.exceptions;

public class ProductMinimumPriceException extends RuntimeException{

    public ProductMinimumPriceException() {
        this("Minimum price musst be greater than zero");
    }

    public ProductMinimumPriceException(String message) {
        super(message);
    }

    public ProductMinimumPriceException(double minPrice) {
        this("Minimum price must be greater than zero, but was " + minPrice);
    }
}
