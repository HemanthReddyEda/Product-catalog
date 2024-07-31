package com.Ecommerce_website.Product_catalog.repository;

import com.Ecommerce_website.Product_catalog.model.Productmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Productrepo extends JpaRepository<Productmodel, Integer> {
    List<Productmodel> findByCategory(String category);

    List<Productmodel> findByPriceLessThan(Long price);

    List<Productmodel> findAll();
}
