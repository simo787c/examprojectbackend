package com.example.demo.Product.Controller;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable("id") Long id){
        Optional<Product> item = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Product %d not found.".formatted(id))));
        return ResponseEntity.ok().body(item.get());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam("query") String query) {
        return ResponseEntity.ok(service.search(query));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product obj) {
        Product item = service.create(obj);
        return ResponseEntity.ok().body(item);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(@PathVariable("id") Long id, @Valid @RequestBody Product obj) {
        Optional<Product> item = Optional.ofNullable(service.update(id, obj).orElseThrow(() -> {
            throw new RuntimeException("Product %d not found".formatted(id));
        }));

        return ResponseEntity.ok().body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Reservation %d not found.".formatted(id)));

        Product delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
