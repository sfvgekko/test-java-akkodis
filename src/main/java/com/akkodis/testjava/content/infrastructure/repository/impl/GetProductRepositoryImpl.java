package com.akkodis.testjava.content.infrastructure.repository.impl;

import com.akkodis.testjava.content.application.mapper.ProductMapper;
import com.akkodis.testjava.content.domain.entity.Product;
import com.akkodis.testjava.content.domain.repository.GetProductRepository;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;
import com.akkodis.testjava.content.infrastructure.repository.entity.ProductJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetProductRepositoryImpl implements GetProductRepository {


    private final ProductMapper mapper;

    private final EntityManager entityManager;


    @Override
    public List<Product> getProducts(ProductFilters filters) {

        TypedQuery<ProductJpa> query = getQuery(filters);

        List<ProductJpa> resultList = query.getResultList();

        return resultList.stream().map(mapper::jpaToDomain).collect(Collectors.toList());
    }


    protected TypedQuery<ProductJpa> getQuery(ProductFilters filters){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ProductJpa> cq = cb.createQuery(ProductJpa.class);

        Root<ProductJpa> root = cq.from(ProductJpa.class);

        List<Predicate> predicates = getPredicates(filters, cb, root);

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq);
    }


    protected List<Predicate> getPredicates (ProductFilters filters, CriteriaBuilder cb, Root<ProductJpa> root){

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getBrandId() != null) {
            predicates.add(cb.equal(root.get("brandId"), filters.getBrandId()));
        }

        if (filters.getProductId() != null) {
            predicates.add(cb.equal(root.get("productId"), filters.getProductId()));
        }

        if (filters.getApplicationDate() != null) {
            Predicate startDate = cb.lessThanOrEqualTo(root.get("startDate"), filters.getApplicationDate());
            Predicate endDate = cb.greaterThanOrEqualTo(root.get("endDate"), filters.getApplicationDate());
            predicates.add(cb.and(startDate, endDate));
        }

        return predicates;
    }

}
