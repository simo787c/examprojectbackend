package com.example.demo.Member.Service;

import com.example.demo.Member.Model.Member;
import com.example.demo.Member.Repository.MemberRepo;
import com.example.demo.Reservation.Model.Reservation;
import com.example.demo.Reservation.Repository.ReservationRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepo repository;

    private final ReservationRepo reservationRepo;

    public MemberService(MemberRepo repository, ReservationRepo reservationRepo) {
        this.repository = repository;
        this.reservationRepo = reservationRepo;
    }

    public Member create(Member member) {
        return repository.save(member);
    }

    public Iterable<Member> findAll(){
        return repository.findAll();
    }

    public Optional<Member> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Member> update(Long id, Member member) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(member));
                });
    }
    public Member delete(Long id) {

        List<Reservation> reservationList = (List<Reservation>) reservationRepo.findAll();

        for (int i = 0; i < reservationList.size(); i++){
            if(reservationList.get(i).getMember().getId() == id){
                reservationRepo.deleteById(reservationList.get(i).getId());
            }
        }

        repository.deleteById(id);
        return null;
    }
}
