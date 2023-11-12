package com.cdcm.apirestpsql.controller;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.model.entity.Person;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonDto personDto) {
       Person person = personService.create(personDto);
       return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

}
