package com.example.demo.Member.Repository;

import com.example.demo.Member.Model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends CrudRepository<Member,Long> {

    @Query("SELECT m FROM Member m ORDER BY m.firstName")
    List<Member> findAllOrderByFirstName();
}
