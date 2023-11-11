package com.cdcm.apirestpsql.controller;

import com.cdcm.apirestpsql.dto.PersonDto;
import com.cdcm.apirestpsql.dto.ProductDto;
import com.cdcm.apirestpsql.entity.Person;
import com.cdcm.apirestpsql.entity.Product;
import com.cdcm.apirestpsql.service.interfaces.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person person;

    @BeforeEach
    void setUp() {
        Product product1 = Product.builder()
                .id(1L)
                .name("oil")
                .price(BigDecimal.valueOf(10))
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("rice")
                .price(BigDecimal.valueOf(5))
                .build();
        person = Person.builder()
                .id(1L)
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .products(List.of(product1, product2))
                .build();
    }

    @Test
    public void savePerson() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name("oil")
                .price(BigDecimal.valueOf(10))
                .build();
        ProductDto productDto1 = ProductDto.builder()
                .name("rice")
                .price(BigDecimal.valueOf(5))
                .build();
        PersonDto personDto = PersonDto.builder()
                .name("Pepe")
                .email("pepe@gmail.com")
                .password("123456")
                .products(List.of(productDto, productDto1))
                .build();
        Mockito.when(personService.createPerson(personDto)).thenReturn(person);
        mockMvc.perform(post("/api/v1/person").contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\":\"Pepe\"," +
                        "\"email\":\"pepe@gmail.com\"," +
                        "\"password\":\"123456\"," +
                        "\"products\":[" +
                        "{\"name\":\"oil\",\"price\":\"10\"}," +
                        "{\"name\":\"rice\",\"price\":\"5\"}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(person.getName()));
    }
}