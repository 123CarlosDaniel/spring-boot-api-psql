package com.cdcm.apirestpsql.controller;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.model.entity.Person;
import com.cdcm.apirestpsql.model.payload.CustomResponse;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<CustomResponse> createPerson(@RequestBody @Valid PersonDto personDto) {
       Person person = personService.create(personDto);
       return new ResponseEntity<>(CustomResponse.builder()
               .error(false)
               .data(person)
               .status(201)
               .errorData(null)
               .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getPersons() {
        List<Person> personList = personService.findAll();
        return new ResponseEntity<>(CustomResponse.builder()
                .error(false)
                .data(personList)
                .status(200)
                .errorData(null)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPerson(@PathVariable Long id) {
        Person person = personService.findById(id);
        return new ResponseEntity<>(CustomResponse.builder()
                .error(false)
                .data(person)
                .status(200)
                .errorData(null)
                .build(), HttpStatus.OK);
    }

}
