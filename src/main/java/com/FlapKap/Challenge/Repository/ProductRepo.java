package com.FlapKap.Challenge.Repository;


import com.FlapKap.Challenge.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByProductName (String name);

    List<Product> findAllBySellerId (long sellerId);

}