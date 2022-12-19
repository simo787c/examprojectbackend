package com.example.demo;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ExamprojectbackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ProductRepo productRepo;
	@Test
	void createProduct(){
		Product product = new Product("Coca Cola Original",24.99,2);

		Product actual = productRepo.save(product);

		assertThat(actual).isEqualTo(product);
	}

	@Test
	void findProductById(){
		Product product1 = new Product("Coca Cola Original",24.99,2);

		Product product2 = new Product("Faxe Kondi",24.99,2);

		List<Product> products = new ArrayList<>();

		products.add(product1);
		products.add(product2);

		productRepo.saveAll(products);

		Optional<Product> actual = productRepo.findById(2L);

		assertThat(actual.get().getId()).isEqualTo(2L);
	}

	@Test
	void findProductByName(){
		Product product1 = new Product("Coca Cola Original",24.99,2);

		Product product2 = new Product("Faxe Kondi",24.99,2);

		List<Product> products = new ArrayList<>();

		products.add(product1);
		products.add(product2);

		productRepo.saveAll(products);

		List<Product> actual = productRepo.findByNameContainsIgnoreCase("KONDI");

		assertThat(actual.get(0).getName()).isEqualTo(products.get(1).getName());
	}

}
