package com.cdcm.apirestpsql.service.interfaces;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.model.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person create(PersonDto personDto);
    Optional<Person> findPersonById(Long id);

    List<Person> findAll();

    Person findById(Long id);
}
