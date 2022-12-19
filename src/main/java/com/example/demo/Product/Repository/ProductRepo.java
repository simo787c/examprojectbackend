package com.example.demo.Product.Repository;

import com.example.demo.Product.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Long> {
    List<Product> findByNameContainsIgnoreCase(String name);
}
