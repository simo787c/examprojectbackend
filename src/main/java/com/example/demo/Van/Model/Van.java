package com.example.demo.Van.Model;

import com.example.demo.Delivery.Model.Delivery;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Van")
public class Van {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private double capacity;


    @OneToMany
    private Set<Delivery> deliveries;

    public Van(String brand, String model, double capacity, Set<Delivery> deliveries) {
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.deliveries = deliveries;
    }
}
