package com.akkodis.testjava.content.infrastructure.controller;

import com.akkodis.testjava.content.DataTest;
import com.akkodis.testjava.content.application.GetProductsUseCase;
import com.akkodis.testjava.content.application.mapper.ProductMapper;
import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductOutputDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GetProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductsUseCase useCase;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("brandId", "test"),
                Arguments.of("productId", "test"),
                Arguments.of("applicationDate", "test")
        );
    }


    @Test
    void getProducts_with_no_filters() throws Exception {
        // Given
        ProductFilters filters = new ProductFilters();

        Product expectedProduct = DataTest.getProduct1();

        // When
        when(useCase.getProductApplicationPrice(filters)).thenReturn(expectedProduct);

        // Act and Assert
        String responseJson = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        ProductOutputDto output = objectMapper.readValue(responseJson, new TypeReference<>() {});

        //Test product conversion
        assertEquals(expectedProduct.getBrandId(), output.getBrandId());
        assertEquals(expectedProduct.getStartDate(), output.getStartDate());
        assertEquals(expectedProduct.getEndDate(), output.getEndDate());
        assertEquals(expectedProduct.getPriceList(), output.getPriceList());
        assertEquals(expectedProduct.getProductId(), output.getProductId());
        assertEquals(expectedProduct.getPrice(), output.getPrice());
    }


    @Test
    void getProducts_with_filters() throws Exception {
        // Given
        ProductFilters filters = new ProductFilters(1, 35444, "2020-06-14 10:00:00");

        Product expectedProduct = DataTest.getProduct1();

        // When
        when(useCase.getProductApplicationPrice(filters)).thenReturn(expectedProduct);

        // Act and Assert
        String responseJson = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("brandId", "1")
                .param("productId", "35444")
                .param("applicationDate", "2020-06-14 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        ProductOutputDto output = objectMapper.readValue(responseJson, new TypeReference<>() {});

        //Test product conversion
        assertEquals(expectedProduct.getBrandId(), output.getBrandId());
        assertEquals(expectedProduct.getStartDate(), output.getStartDate());
        assertEquals(expectedProduct.getEndDate(), output.getEndDate());
        assertEquals(expectedProduct.getPriceList(), output.getPriceList());
        assertEquals(expectedProduct.getProductId(), output.getProductId());
        assertEquals(expectedProduct.getPrice(), output.getPrice());
    }



    @ParameterizedTest
    @MethodSource("testCases")
    void getProducts_with_wrong_parameters(String param, String value) throws Exception {
        // Given testCases params

        // When
        when(useCase.getProductApplicationPrice(any())).thenReturn(new Product());

        // Act and Assert
        mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param(param, value))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }

}

