package com.example.demo.Delivery.Controller;

import com.example.demo.Delivery.Model.Delivery;
import com.example.demo.Delivery.Service.DeliveryService;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/deliveries")
public class DeliveryController {
    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Delivery>> findAll() {
        Iterable<Delivery> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> find(@PathVariable("id") Long id){
        Optional<Delivery> item = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Delivery %d not found.".formatted(id))));
        return ResponseEntity.ok().body(item.get());
    }

    @PostMapping
    public ResponseEntity<Delivery> create(@Valid @RequestBody Delivery obj) {
        Delivery item = service.create(obj);
        return ResponseEntity.ok().body(item);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Delivery> patch(@PathVariable("id") Long id, @Valid @RequestBody Delivery obj) {
        Optional<Delivery> item = Optional.ofNullable(service.update(id, obj).orElseThrow(() -> {
            throw new RuntimeException("Delivery %d not found".formatted(id));
        }));

        return ResponseEntity.ok().body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Delivery> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Delivery %d not found.".formatted(id)));

        Delivery delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
