package com.spring.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.java.entity.ProductEntity;
import com.spring.java.productmodel.ProductModel;

@Repository

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}