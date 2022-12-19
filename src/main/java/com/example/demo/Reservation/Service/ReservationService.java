package com.example.demo.Reservation.Service;

import com.example.demo.Reservation.Model.Reservation;
import com.example.demo.Reservation.Repository.ReservationRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo repository;

    public ReservationService(ReservationRepo repository) {this.repository = repository;}

    public Reservation create(Reservation reservation) {

        boolean rentalDateExists = repository.existsReservationsByCarAndRentalDate(reservation.getCar(),reservation.getRentalDate());

        if (!rentalDateExists){
            return repository.save(reservation);
        }
        System.out.println("Date is already taken");
        return null;
    }

    public Iterable<Reservation> findAll(){
        return repository.findAll();
    }

    public Optional<Reservation> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Reservation> update(Long id, Reservation reservation) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(reservation));
                });
    }

    public Reservation delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
