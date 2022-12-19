package com.example.demo.ProductOrder.Model;

import com.example.demo.Product.Model.Product;
import com.example.demo.Delivery.Model.Delivery;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ProductOrder")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;

    //May upgrade to ManyToMany so one order contains list of product.
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public ProductOrder(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    //remember to add constructor to include delivery

    public ProductOrder updateFrom(ProductOrder productOrder) {
        if(productOrder.quantity!=null) {this.quantity = productOrder.quantity;}
        if(productOrder.delivery!=null) {this.delivery = productOrder.delivery;}
        if(productOrder.product!=null) {this.product = productOrder.product;}
        return this;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
