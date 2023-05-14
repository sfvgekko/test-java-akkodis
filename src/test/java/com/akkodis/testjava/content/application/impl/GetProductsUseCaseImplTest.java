package com.akkodis.testjava.content.application.impl;

import com.akkodis.testjava.content.DataTest;
import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.domain.repository.GetProductRepository;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class GetProductsUseCaseImplTest {

    @MockBean
    private GetProductRepository repo;

    @Autowired
    private GetProductsUseCaseImpl useCase;

    @Test
    void getProducts() {
        // Given
        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(DataTest.getProduct1());
        expectedProductList.add(DataTest.getProduct2());

        // When
        when(repo.getProducts(any())).thenReturn(expectedProductList);

        // Act
        List<Product> productList = useCase.getProducts(new ProductFilters());

        // Then
        Assertions.assertEquals(expectedProductList.size(), productList.size());

    }

}