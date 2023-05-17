package com.akkodis.testjava.content.application;

import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;


public interface GetProductsUseCase {

    Product getProductApplicationPrice(ProductFilters filters);

}
