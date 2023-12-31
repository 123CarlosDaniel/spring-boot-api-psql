package com.cdcm.apirestpsql.service.interfaces;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.model.entity.Person;

import java.util.List;

public interface PersonService {
    Person create(PersonDto personDto);

    List<Person> findAll();

    Person findById(Long id);
}
