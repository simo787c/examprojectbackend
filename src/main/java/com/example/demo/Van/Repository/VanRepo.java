package com.example.demo.Van.Repository;

import com.example.demo.Van.Model.Van;
import org.springframework.data.repository.CrudRepository;

public interface VanRepo extends CrudRepository<Van,Long> {
}
