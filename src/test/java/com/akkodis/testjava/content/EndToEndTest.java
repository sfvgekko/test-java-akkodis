package com.akkodis.testjava.content;

import com.akkodis.testjava.content.infrastructure.controller.dto.ProductOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
class EndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("1", "35455", "2020-06-14 10:00:00", 1),
                Arguments.of("1", "35455", "2020-06-14 16:00:00", 2),
                Arguments.of("1", "35455", "2020-06-14 21:00:00", 1),
                Arguments.of("1", "35455", "2020-06-15 10:00:00", 3),
                Arguments.of("1", "35455", "2020-06-16 21:00:00", 4)
        );
    }


    @ParameterizedTest
    @MethodSource("testCases")
    void getProductsEndToEndTest(String brandId, String productId, String applicationDate, int expectedPriceList) throws Exception {
        // Given Source:data-test.sql
        // testCases

        // When
        ProductOutputDto result = performRequest(brandId, productId, applicationDate);

        // Then
        doAssertions(brandId, productId, applicationDate, result, expectedPriceList);
    }


    private ProductOutputDto performRequest (String brandId, String productId, String applicationDate) throws Exception {

        String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                .param("brandId", brandId)
                .param("productId", productId)
                .param("applicationDate", applicationDate)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        return  objectMapper.readValue(responseJson, ProductOutputDto.class);
    }


    private void doAssertions(String brandId, String productId, String applicationDate,
                              ProductOutputDto result, int expectedPriceList){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        assertThat(result.getProductId()).isEqualTo(Integer.valueOf(productId));

        assertThat(result.getBrandId()).isEqualTo(Integer.valueOf(brandId));

        assertThat(result.getStartDate().isBefore(LocalDateTime.parse(applicationDate, formatter))).isTrue();

        assertThat(result.getEndDate().isAfter(LocalDateTime.parse(applicationDate, formatter))).isTrue();

        assertThat(result.getPriceList()).isEqualTo(expectedPriceList);

    }



}