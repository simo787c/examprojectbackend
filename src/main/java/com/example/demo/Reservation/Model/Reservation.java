package com.example.demo.Reservation.Model;

import com.example.demo.Car.Model.Car;
import com.example.demo.Member.Model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservationDate")
    private Date reservationDate;

    @Column(name = "rentalDate")
    private Date rentalDate;


    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;


    public Reservation(Date reservationDate, Date rentalDate) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

    public Reservation(Date reservationDate, Date rentalDate, Car car, Member member) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
        this.car = car;
        this.member = member;
    }

    public Reservation updateFrom(Reservation reservation) {
        if(reservation.reservationDate!=null) {this.reservationDate = reservation.reservationDate;}
        if(reservation.rentalDate!=null) {this.rentalDate = reservation.rentalDate;}
        if(reservation.car!=null) {this.car = reservation.car;}
        if(reservation.member!=null) {this.member = reservation.member;}
        return this;
    }
}
