package com.example.demo.Member.Controller;

import com.example.demo.Member.Model.Member;
import com.example.demo.Member.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Member>> findAll() {
        Iterable<Member> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> find(@PathVariable("id") Long id){
        Optional<Member> item = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Member %d not found.".formatted(id))));
        return ResponseEntity.ok().body(item.get());
    }

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody Member obj) {
        Member item = service.create(obj);
        return ResponseEntity.ok().body(item);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Member> patch(@PathVariable("id") Long id, @Valid @RequestBody Member obj) {
        Optional<Member> item = Optional.ofNullable(service.update(id, obj).orElseThrow(() -> {
            throw new RuntimeException("Member %d not found".formatted(id));
        }));

        return ResponseEntity.ok().body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Member %d not found.".formatted(id)));

        Member delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
