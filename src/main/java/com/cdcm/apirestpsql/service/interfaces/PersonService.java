package com.cdcm.apirestpsql.service.interfaces;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.entity.Person;

public interface PersonService {
    Person createPerson(PersonDto personDto);
}
