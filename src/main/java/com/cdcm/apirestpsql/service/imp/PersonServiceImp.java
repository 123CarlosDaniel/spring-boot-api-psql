package com.cdcm.apirestpsql.service.imp;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.entity.Product;
import com.cdcm.apirestpsql.repository.PersonRepository;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person createPerson(PersonDto personDto) {
        Person person = Person.builder()
                .name(personDto.getName())
                .email(personDto.getEmail())
                .password(personDto.getPassword())
                .products(personDto.getProducts().stream()
                        .map(prod -> Product.builder()
                                .price(prod.getPrice())
                                .name(prod.getName()).build())
                        .collect(Collectors.toList()))
                .build();
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }
}
