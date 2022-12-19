package com.example.demo.Car.Repository;

import com.example.demo.Car.Model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car,Long> {
}
