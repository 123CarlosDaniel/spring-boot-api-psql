package com.cdcm.apirestpsql.service.interfaces;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person createPerson(PersonDto personDto);
    Optional<Person> findPersonById(Long id);

    List<Person> getAll();
}
