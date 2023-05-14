package com.akkodis.testjava.content.application.impl;

import com.akkodis.testjava.content.application.GetProductsUseCase;
import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.domain.repository.GetProductRepository;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetProductsUseCaseImpl implements GetProductsUseCase {

    private final GetProductRepository repo;


    public List<Product> getProducts(ProductFilters filters){

        return repo.getProducts(filters);

    }
}


