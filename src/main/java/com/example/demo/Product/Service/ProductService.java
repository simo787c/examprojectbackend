package com.example.demo.Product.Service;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Repository.ProductRepo;
import com.example.demo.ProductOrder.Model.ProductOrder;
import com.example.demo.ProductOrder.Repository.ProductOrderRepo;
import com.example.demo.Reservation.Model.Reservation;
import com.example.demo.Reservation.Repository.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo repository;
    private final ProductOrderRepo PORepo;

    public ProductService(ProductRepo repository, ProductOrderRepo PORepo) {
        this.repository = repository;
        this.PORepo = PORepo;
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

        List<ProductOrder> productOrders = (List<ProductOrder>) PORepo.findAll();

        for (int i = 0; i < productOrders.size(); i++){
            if(productOrders.get(i).getProduct().getId() == id){
                PORepo.deleteById(productOrders.get(i).getId());
            }
        }

        repository.deleteById(id);
        return null;
    }
}
