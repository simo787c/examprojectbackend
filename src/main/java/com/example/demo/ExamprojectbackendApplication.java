package com.example.demo;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Repository.ProductRepo;
import com.example.demo.ProductOrder.Repository.ProductOrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExamprojectbackendApplication {

	private static final Logger log = LoggerFactory.getLogger(ExamprojectbackendApplication.class);

	public static void main(String[] args) {SpringApplication.run(ExamprojectbackendApplication.class, args);}

	@Bean
	public CommandLineRunner importData(ProductRepo productRepo,
										ProductOrderRepo productOrderRepo
										){

		return (args) -> {


			final List<Product> products = new ArrayList<>();
			products.add(new Product("Coca Cola Original",24.99,200));
			products.add(new Product("Faxe Kondi",24.99,200));
			products.add(new Product("Grill Kylling",24.99,1500));
			products.add(new Product("Gulerødder x 6",24.99,390));
			productRepo.saveAll(products);

		};
	}
}
