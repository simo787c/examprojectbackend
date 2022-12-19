package com.example.demo.Product.Model;

import com.example.demo.Reservation.Model.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    //Weight is in Kg
    @Column(name = "weight")
    private Double weight;

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Product updateFrom(Product product) {
        if(product.name!=null) {this.name = product.name;}
        if(product.price!=null) {this.price = product.price;}
        if(product.weight!=null) {this.weight = product.weight;}
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
