package com.example.demo;

import com.example.demo.Car.Model.Car;
import com.example.demo.Car.Repository.CarRepo;
import com.example.demo.Member.Model.Member;
import com.example.demo.Member.Repository.MemberRepo;
import com.example.demo.Reservation.Model.Reservation;
import com.example.demo.Reservation.Repository.ReservationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ExamprojectbackendApplication {

	private static final Logger log = LoggerFactory.getLogger(ExamprojectbackendApplication.class);

	public static void main(String[] args) {SpringApplication.run(ExamprojectbackendApplication.class, args);}

	@Bean
	public CommandLineRunner importData(MemberRepo memberRepo,
										CarRepo carRepo,
										ReservationRepo reservationRepo
										){

		return (args) -> {

			final List<Member> members = new ArrayList<>();
			members.add(new Member("Johnny","Johnson","Frederiksberg vej 34", "København", 2500, false,"10.0"));
			members.add(new Member("Lars","Larsen","Princesse vej 90", "København", 2400, false,"20.0"));
			memberRepo.saveAll(members);

			final List<Car> cars = new ArrayList<>();
			cars.add(new Car("Audi","A4",300,30));
			cars.add(new Car("Nissan","Air",460,14));
			carRepo.saveAll(cars);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format.parse ( "2022-12-19 00:00:00" );

			Reservation reservation = new Reservation(date,date,cars.get(0),members.get(0));
			reservationRepo.save(reservation);


		};
	}
}
