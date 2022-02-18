package com.example.demo2.esperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/es/person")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service){
        this.service=service;
    }
    @PostMapping
    public void save(@RequestBody final Person person){
        service.save(person);
    }
    @GetMapping("/{id}")
    public Person findById(@PathVariable final String id){
        return service.findById(id);
    }
}
