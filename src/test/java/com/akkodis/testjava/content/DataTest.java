package com.akkodis.testjava.content;

import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.repository.entity.ProductJpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);


    public static Product getProduct1() {
        return Product.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", formatter))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(35.50)
                .currency("EUR")
                .build();
    }


    public static Product getProduct2() {
        return Product.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 15:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14 18:30:00", formatter))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(25.45)
                .currency("EUR")
                .build();
    }


    public static Product getProduct3() {
        return Product.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 00:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-06-15 11:00:00", formatter))
                .priceList(3)
                .productId(35455)
                .priority(1)
                .price(30.50)
                .currency("EUR")
                .build();
    }


    public static Product getProduct4() {
        return Product.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 16:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", formatter))
                .priceList(4)
                .productId(35455)
                .priority(1)
                .price(38.95)
                .currency("EUR")
                .build();
    }


    public static ProductJpa getProductJpa1() {
        return ProductJpa.builder()
                .id(1L)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", formatter))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(35.50)
                .currency("EUR")
                .build();
    }


    public static ProductJpa getProductJpa2() {
        return ProductJpa.builder()
                .id(2L)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 15:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14 18:30:00", formatter))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(25.45)
                .currency("EUR")
                .build();
    }


    public static ProductJpa getProductJpa3() {
        return ProductJpa.builder()
                .id(3L)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 00:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-06-15 11:00:00", formatter))
                .priceList(3)
                .productId(35455)
                .priority(1)
                .price(30.50)
                .currency("EUR")
                .build();
    }


    public static ProductJpa getProductJpa4() {
        return ProductJpa.builder()
                .id(4L)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 16:00:00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", formatter))
                .priceList(4)
                .productId(35455)
                .priority(1)
                .price(38.95)
                .currency("EUR")
                .build();
    }

}
