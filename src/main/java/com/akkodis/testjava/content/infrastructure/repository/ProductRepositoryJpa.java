package com.akkodis.testjava.content.infrastructure.repository;

import com.akkodis.testjava.content.infrastructure.repository.entity.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductJpa, Long>{


}
