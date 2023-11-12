package com.cdcm.apirestpsql.service.interfaces;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.entity.Product;
import com.cdcm.apirestpsql.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @MockBean
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        Product product1 = Product.builder()
                .name("oil")
                .price(BigDecimal.valueOf(10))
                .build();
        Person person = Person.builder()
                .id(1L)
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .products(List.of(product1))
                .build();
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
    }

    @Test
    public void createPersonWithoutProducts() {
        PersonDto personDto = PersonDto.builder()
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .build();
        Person personToSave = Person.builder()
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .build();
        Mockito.when(personRepository.save(personToSave)).thenReturn(personToSave);

        Person result = personService.createPerson(personDto);
        assertEquals(personDto.getName(), result.getName());
        assertEquals(personDto.getEmail(), result.getEmail());
        verify(personRepository).save(personToSave);
    }

    @Test
    public void findPersonById() {
        Long id = 1L;
        Person person = personService.findPersonById(id).get();
        assertEquals(person.getId(), id);
    }
}