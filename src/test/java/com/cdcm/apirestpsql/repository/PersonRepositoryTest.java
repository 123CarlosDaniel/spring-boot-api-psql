package com.cdcm.apirestpsql.repository;

import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        Product product1 = Product.builder()
                .name("oil")
                .price(BigDecimal.valueOf(10))
                .build();
        Product product2 = Product.builder()
                .name("rice")
                .price(BigDecimal.valueOf(5))
                .build();
        Person person = Person.builder()
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .products(List.of(product1, product2))
                .build();
        testEntityManager.persist(person);
    }

    @Test
    public void getPersons() {
        List<Person> personList = personRepository.findAll();
        System.out.println("personList = " + personList);
    }

    @Test
    public void savePerson() {
        Person person = personRepository.findPersonByEmail("pepe@gmail.com").get();
        assertEquals(person.getName(), "Pepe");
    }
}




