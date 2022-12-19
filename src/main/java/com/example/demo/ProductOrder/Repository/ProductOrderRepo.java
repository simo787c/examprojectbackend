package com.example.demo.ProductOrder.Repository;

import com.example.demo.ProductOrder.Model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepo extends CrudRepository<ProductOrder,Long> {
}
