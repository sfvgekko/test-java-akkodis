package com.akkodis.testjava.content.infrastructure.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Data
public class ProductFilters {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    Integer brandId;

    Integer productId;

    LocalDateTime applicationDate;


    public ProductFilters() {
    }

    public ProductFilters(Integer brandId, Integer productId, String applicationDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        this.brandId = brandId;

        this.productId = productId;

        this.applicationDate = Objects.isNull(applicationDate) ? null :
                               LocalDateTime.parse(applicationDate, formatter);

    }
}
