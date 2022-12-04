package com.belyaeva.services.abstractions;

public interface ProductFacade<T, U> {
    T getProducts(U model);
}
