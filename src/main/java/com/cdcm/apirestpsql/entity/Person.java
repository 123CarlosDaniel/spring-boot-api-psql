package com.cdcm.apirestpsql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;

}
