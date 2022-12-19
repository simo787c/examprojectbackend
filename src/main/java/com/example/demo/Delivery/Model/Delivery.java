package com.example.demo.Delivery.Model;

import com.example.demo.Product.Model.Product;
import com.example.demo.ProductOrder.Model.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "fromWarehouse")
    private String fromWarehouse;

    @Column(name = "destination")
    private String destination;

    @OneToMany
    private Set<ProductOrder> productOrders;

    public Delivery(Date deliveryDate, String fromWarehouse, String destination) {
        this.deliveryDate = deliveryDate;
        this.fromWarehouse = fromWarehouse;
        this.destination = destination;
    }

    public Delivery(Date deliveryDate, String fromWarehouse, String destination, Set<ProductOrder> productOrders) {
        this.deliveryDate = deliveryDate;
        this.fromWarehouse = fromWarehouse;
        this.destination = destination;
        this.productOrders = productOrders;
    }

//    private ProductOrder getProductOrder(Long id){
//        productOrders.ge
//
//    }

    public Delivery updateFrom(Delivery delivery) {
        if(delivery.deliveryDate!=null) {this.deliveryDate = delivery.deliveryDate;}
        if(delivery.fromWarehouse!=null) {this.fromWarehouse = delivery.fromWarehouse;}
        if(delivery.destination!=null) {this.destination = delivery.destination;}
        if(delivery.productOrders!=null) {this.productOrders = delivery.productOrders;}
        return this;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", deliveryDate=" + deliveryDate +
                ", fromWarehouse='" + fromWarehouse + '\'' +
                ", destination='" + destination + '\'' +
                ", productOrders=" + productOrders +
                '}';
    }
}