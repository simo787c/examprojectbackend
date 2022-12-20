package com.example.demo.Product.Service;

import com.example.demo.Delivery.Model.Delivery;
import com.example.demo.Delivery.Service.DeliveryService;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Repository.ProductRepo;
import com.example.demo.ProductOrder.Model.ProductOrder;
import com.example.demo.ProductOrder.Repository.ProductOrderRepo;
import com.example.demo.ProductOrder.Service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo repository;
    private final ProductOrderService POService;
    private final DeliveryService deliveryService;

    public ProductService(ProductRepo repository, ProductOrderService POService, DeliveryService deliveryService) {
        this.repository = repository;
        this.POService = POService;
        this.deliveryService = deliveryService;
    }

    public Product create(Product product) {

        return repository.save(product);
    }

    public Iterable<Product> findAll(){
        return repository.findAll();
    }

    public Optional<Product> find(Long id) {
        return repository.findById(id);
    }

    public List<Product> search(String query){
        return repository.findByNameContainsIgnoreCase(query);
    }

    public Optional<Product> update(Long id, Product product) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(product));
                });
    }

    public Product delete(Long id) {

        List<ProductOrder> productOrders = (List<ProductOrder>) POService.findAll();


        for (int i = 0; i < productOrders.size(); i++){
            if(productOrders.get(i).getProduct().getId() == id){
                deliveryService.delete(productOrders.get(i).getDelivery().getId());
            }
        }

        repository.deleteById(id);
        return null;
    }
}
