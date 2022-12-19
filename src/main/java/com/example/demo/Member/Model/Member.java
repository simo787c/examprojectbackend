package com.example.demo.Member.Model;

import com.example.demo.Reservation.Model.Reservation;
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
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "ranking")
    private String ranking;

    @JsonBackReference
    @OneToMany(mappedBy = "id")
    private Set<Reservation> reservations;

    public Member(String firstName, String lastName, String street, String city, int zip, Boolean approved, String ranking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    public Member updateFrom(Member member) {
        if(member.firstName!=null) {this.firstName = member.firstName;}
        if(member.lastName!=null) {this.lastName = member.lastName;}
        if(member.street!=null) {this.street = member.street;}
        if(member.city!=null) {this.city = member.city;}
        if(member.zip!=null) {this.zip = member.zip;}
        if(member.approved!=null) {this.approved = member.approved;}
        if(member.ranking!=null) {this.ranking = member.ranking;}
        return this;
    }



    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", approved='" + approved + '\'' +
                ", ranking='" + ranking + '\'' +
                '}';
    }
}
