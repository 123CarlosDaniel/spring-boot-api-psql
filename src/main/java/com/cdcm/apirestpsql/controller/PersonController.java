package com.cdcm.apirestpsql.controller;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonDto personDto) {
       Person person = personService.createPerson(personDto);
       return ResponseEntity.ok(person);
    }


}
