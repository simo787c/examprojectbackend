package com.example.demo.Delivery.Repository;

import com.example.demo.Delivery.Model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo extends CrudRepository<Delivery,Long> {
}
