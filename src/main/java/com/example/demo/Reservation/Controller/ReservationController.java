package com.example.demo.Reservation.Controller;

import com.example.demo.Member.Model.Member;
import com.example.demo.Member.Service.MemberService;
import com.example.demo.Reservation.Model.Reservation;
import com.example.demo.Reservation.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Reservation>> findAll() {
        Iterable<Reservation> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> find(@PathVariable("id") Long id){
        Optional<Reservation> item = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Reservation %d not found.".formatted(id))));
        return ResponseEntity.ok().body(item.get());
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation obj) {
        Reservation item = service.create(obj);
        return ResponseEntity.ok().body(item);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> patch(@PathVariable("id") Long id, @Valid @RequestBody Reservation obj) {
        Optional<Reservation> item = Optional.ofNullable(service.update(id, obj).orElseThrow(() -> {
            throw new RuntimeException("Reservation %d not found".formatted(id));
        }));

        return ResponseEntity.ok().body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Reservation %d not found.".formatted(id)));

        Reservation delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
