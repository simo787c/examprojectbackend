package com.example.demo.Delivery.Repository;

import com.example.demo.Delivery.Model.Delivery;
import com.example.demo.Product.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepo extends CrudRepository<Delivery,Long> {
    List<Delivery> findByDestinationContainsIgnoreCase(String name);
}
