package com.example.demo.ProductOrder.Service;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Repository.ProductRepo;
import com.example.demo.ProductOrder.Model.ProductOrder;
import com.example.demo.ProductOrder.Repository.ProductOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {
    private final ProductOrderRepo repository;

    public ProductOrderService(ProductOrderRepo repository) {this.repository = repository;}

    public ProductOrder create(ProductOrder productOrder) {

        return repository.save(productOrder);
    }

    public Iterable<ProductOrder> findAll(){
        return repository.findAll();
    }

    public Optional<ProductOrder> find(Long id) {
        return repository.findById(id);
    }

    public Optional<ProductOrder> update(Long id, ProductOrder productOrder) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(productOrder));
                });
    }

    public ProductOrder delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
