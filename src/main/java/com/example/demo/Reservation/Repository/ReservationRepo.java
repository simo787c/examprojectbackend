package com.example.demo.Reservation.Repository;

import com.example.demo.Car.Model.Car;
import com.example.demo.Reservation.Model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation,Long> {

    boolean existsReservationsByCarAndRentalDate(Car car, Date date);
}
