package com.akkodis.testjava.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private int brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int priceList;

    private int productId;

    private int priority;

    private double price;

    private String currency;

}
