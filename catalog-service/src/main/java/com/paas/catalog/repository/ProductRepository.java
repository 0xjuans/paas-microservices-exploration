package com.paas.catalog.repository;

import com.paas.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos de productos vía Spring Data JPA. */
public interface ProductRepository extends JpaRepository<Product, Long> {}
