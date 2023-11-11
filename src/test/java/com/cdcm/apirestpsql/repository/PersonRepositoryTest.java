package com.cdcm.apirestpsql.repository;

import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void getPersons() {
        List<Person> personList = personRepository.findAll();
        System.out.println("personList = " + personList);
    }

    @Test
    public void savePerson() {
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
        personRepository.save(person);

    }
}




