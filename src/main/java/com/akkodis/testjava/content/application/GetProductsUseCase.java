package com.akkodis.testjava.content.application;

import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;

import java.util.List;

public interface GetProductsUseCase {

    List<Product> getProducts(ProductFilters filters);

}
