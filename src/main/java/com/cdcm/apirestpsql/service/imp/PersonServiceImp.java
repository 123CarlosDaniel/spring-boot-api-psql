package com.cdcm.apirestpsql.service.imp;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.exception.custom.ItemNotFoundException;
import com.cdcm.apirestpsql.model.entity.Person;
import com.cdcm.apirestpsql.model.entity.Product;
import com.cdcm.apirestpsql.repository.PersonRepository;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person create(PersonDto personDto) {
        Person person = Person.builder()
                .name(personDto.getName())
                .email(personDto.getEmail())
                .password(personDto.getPassword())
                .products(personDto.getProducts() != null ?
                        (
                        personDto.getProducts().stream()
                                .map(prod -> Product.builder()
                                        .price(prod.getPrice())
                                        .name(prod.getName()).build())
                                .collect(Collectors.toList())
                        )
                        :Collections.emptyList()
                        )
                .build();
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new ItemNotFoundException("Person not found", HttpStatus.NOT_FOUND);
        }
        return person.get();
    }
}
