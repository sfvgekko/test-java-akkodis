package com.akkodis.testjava.content.application.mapper;

import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductOutputDto;
import com.akkodis.testjava.content.infrastructure.repository.entity.ProductJpa;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductOutputDto domainToOutputDto(Product product);

    Product jpaToDomain(ProductJpa productJpa);

}
