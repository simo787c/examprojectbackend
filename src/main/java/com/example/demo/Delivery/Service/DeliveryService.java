package com.example.demo.Delivery.Service;

import com.example.demo.Delivery.Model.Delivery;
import com.example.demo.Delivery.Repository.DeliveryRepo;
import com.example.demo.ProductOrder.Model.ProductOrder;
import com.example.demo.ProductOrder.Service.ProductOrderService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepo repository;
    private final ProductOrderService POService;

    public DeliveryService(DeliveryRepo repository, ProductOrderService POService) {
        this.repository = repository;
        this.POService = POService;
    }

    public Delivery create(Delivery delivery) {
        //save the new delivery
        delivery = repository.save(delivery);

        //creates ProductOrders
        for(ProductOrder productOrder : delivery.getProductOrders()){
            productOrder.setDelivery(delivery);
            POService.create(productOrder);
        }

        return delivery;
    }

    public Iterable<Delivery> findAll(){
        return repository.findAll();
    }

    public Optional<Delivery> find(Long id) {
        return repository.findById(id);
    }

    public List<Delivery> search(String query){
        return repository.findByDestinationContainsIgnoreCase(query);
    }

    public Optional<Delivery> update(Long id, Delivery delivery) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(delivery));
                });
    }

    public Delivery delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
