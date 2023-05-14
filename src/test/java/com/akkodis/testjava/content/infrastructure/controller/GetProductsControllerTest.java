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

import java.util.ArrayList;
import java.util.List;
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

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(DataTest.getProduct1());
        expectedProductList.add(DataTest.getProduct2());
        expectedProductList.add(DataTest.getProduct3());
        expectedProductList.add(DataTest.getProduct4());

        // When
        when(useCase.getProducts(filters)).thenReturn(expectedProductList);

        // Act and Assert
        String responseJson = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<ProductOutputDto> output = objectMapper.readValue(responseJson, new TypeReference<>() {});

        //Test size
        assertEquals(output.size(), expectedProductList.size());

        //Test first product conversion
        assertEquals(expectedProductList.get(0).getBrandId(), output.get(0).getBrandId());
        assertEquals(expectedProductList.get(0).getStartDate(), output.get(0).getStartDate());
        assertEquals(expectedProductList.get(0).getEndDate(), output.get(0).getEndDate());
        assertEquals(expectedProductList.get(0).getPriceList(), output.get(0).getPriceList());
        assertEquals(expectedProductList.get(0).getProductId(), output.get(0).getProductId());
        assertEquals(expectedProductList.get(0).getPrice(), output.get(0).getPrice());
    }


    @Test
    void getProducts_with_filters() throws Exception {
        // Given
        ProductFilters filters = new ProductFilters(1, 35444, "2020-06-14 10:00:00");

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(DataTest.getProduct1());

        // When
        when(useCase.getProducts(filters)).thenReturn(expectedProductList);

        // Act and Assert
        String responseJson = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("brandId", "1")
                .param("productId", "35444")
                .param("applicationDate", "2020-06-14 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<ProductOutputDto> output = objectMapper.readValue(responseJson, new TypeReference<>() {});

        //Test size
        assertEquals(expectedProductList.size(), output.size());

        //Test first product conversion
        assertEquals(expectedProductList.get(0).getBrandId(), output.get(0).getBrandId());
        assertEquals(expectedProductList.get(0).getStartDate(), output.get(0).getStartDate());
        assertEquals(expectedProductList.get(0).getEndDate(), output.get(0).getEndDate());
        assertEquals(expectedProductList.get(0).getPriceList(), output.get(0).getPriceList());
        assertEquals(expectedProductList.get(0).getProductId(), output.get(0).getProductId());
        assertEquals(expectedProductList.get(0).getPrice(), output.get(0).getPrice());
    }



    @ParameterizedTest
    @MethodSource("testCases")
    void getProducts_with_wrong_parameters(String param, String value) throws Exception {
        // Given testCases params

        // When
        when(useCase.getProducts(any())).thenReturn(new ArrayList<>());

        // Act and Assert
        mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param(param, value))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }

}

