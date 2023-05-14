package com.akkodis.testjava.content.domain.repository;

import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;

import java.util.List;

public interface GetProductRepository {

    List<Product> getProducts(ProductFilters filters);

}
